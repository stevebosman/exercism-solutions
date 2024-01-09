(module
  (memory (export "mem") 1)

  (data (i32.const 20) "Pling")
  (data (i32.const 25) "Plang")
  (data (i32.const 30) "Plong")

  (global $soundLength i32 (i32.const 5))
  (global $zero i32 (i32.const 48))

  ;; calculates number length
  ;; works for positive numbers only
  (func $magnitude (param $input i32) (result i32)
    (local $result i32)
    (if
      (i32.eqz (local.get $input))
      (return (i32.const 0))
    )

    (return 
      (local.tee $result
        (i32.add
          (i32.const 1)
          (call $magnitude (i32.div_u (local.get $input) (i32.const 10)))
        )
      )
    )
  )

  ;; integer to string
  (func $itos (param $input i32) (param $offset i32) (result i32)
    (local $digit i32)
    (local $result i32)

    (local.set $result
      (i32.add
        (local.get $offset)
        (call $magnitude (local.get $input))
      )
    )
    (local.set $offset
      (i32.add
        (local.get $result)
        (i32.const -1)
      )
    )

    (loop $loop
      (local.set $digit (i32.rem_u (local.get $input) (i32.const 10)))
      (local.set $input (i32.div_u (local.get $input) (i32.const 10)))
  
      (i32.store8 (local.get $offset)
                  (i32.add (global.get $zero) (local.get $digit))
      )

      (br_if $loop
        (i32.gt_s
          (local.tee $offset
            (i32.add
              (local.get $offset)
              (i32.const -1)
            )
          )
          (i32.const -1)
        )
      )
    )
    
    (return (local.get $result))
  )

  ;; write string to memory offset if input is a exact multiple.
  (func $addSoundIfMultiple (param $input i32) (param $multiple i32) (param $soundOffset i32) (param $currentLength i32) (result i32)
    (if
      (i32.eqz
        (i32.rem_u (local.get $input) (local.get $multiple))
      )
      (then
        (memory.copy 
          (local.get $currentLength) 
          (local.get $soundOffset) 
          (global.get $soundLength)
        )
        (local.set $currentLength
          (i32.add
            (local.get $currentLength)
            (global.get $soundLength)
          )
        )
      )
    )
    (return (local.get $currentLength))
  )
  
  ;;
  ;; Convert a number into a string of raindrop sounds
  ;;
  ;; @param {i32} input - The number to convert
  ;;
  ;; @returns {(i32,i32)} - Offset and length of raindrop sounds string 
  ;;                        in linear memory.
  ;;
  (func (export "convert") (param $input i32) (result i32 i32)
    (local $length i32)

    (local.set $length
      (call $addSoundIfMultiple 
        (local.get $input) (i32.const 3) (i32.const 20) (local.get $length) 
      )
    )
    (local.set $length
      (call $addSoundIfMultiple 
        (local.get $input) (i32.const 5) (i32.const 25) (local.get $length) 
      )
    )
    (local.set $length
      (call $addSoundIfMultiple 
        (local.get $input) (i32.const 7) (i32.const 30) (local.get $length) 
      )
    )
    (if
      (i32.eqz (local.get $length))
      (then
        (local.set $length
          (call $itos (local.get $input) (i32.const 0))
        )
      )
    )
    (return (i32.const 0) (local.get $length))
  )
)

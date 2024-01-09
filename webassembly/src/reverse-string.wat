(module
  (memory (export "mem") 1)
 
  ;;
  ;; Reverse a string
  ;;
  ;; @param {i32} offset - The offset of the input string in linear memory
  ;; @param {i32} length - The length of the input string in linear memory
  ;;
  ;; @returns {(i32,i32)} - The offset and length of the reversed string in linear memory
  ;;
  (func (export "reverseString") (param $offset i32) (param $length i32) (result i32 i32)
    (local $forwardPosition i32)
    (local $lastPosition i32)
    (local $cache i32)

    (local.set $forwardPosition
      (local.get $offset)
    )
  
    (local.set $lastPosition
      (i32.add
        (local.get $offset)
        (i32.add (local.get $length) (i32.const -1))
      )
    )
  
    (loop $loop
      (local.set $cache
        (i32.load8_u (local.get $forwardPosition))
      )

      (i32.store8
        (local.get $forwardPosition)
        (i32.load8_u (local.get $lastPosition))
      )

      (i32.store8
        (local.get $lastPosition)
        (local.get $cache)
      )

      (br_if $loop 
        (i32.lt_s
          (local.tee $forwardPosition (i32.add (local.get $forwardPosition) (i32.const 1)))
          (local.tee $lastPosition (i32.add (local.get $lastPosition) (i32.const -1)))
        )
      )
    )
    
    (return (local.get $offset) (local.get $length))
  )
)

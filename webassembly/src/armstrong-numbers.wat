(module
  ;; calculates number length
  ;; works for positive numbers only
  (func $numberLength (param $input i32) (result i32)
    (local $result i32)
    (if
      (i32.eqz (local.get $input))
      (return (i32.const 0))
    )
    (return 
      (local.tee $result
        (i32.add
          (i32.const 1)
          (call $numberLength (i32.div_u (local.get $input) (i32.const 10)))
        )
      )
    )
  )

  ;; calculate $input^$power
  (func $pow (param $input i32) (param $power i32) (result i32)
    (local $result i32)
    (local $count i32)

    (local.set $result (i32.const 1))
    (local.set $count (local.get $power))

    (loop $loop
      (local.set $result (i32.mul (local.get $result) (local.get $input)))
      (br_if $loop
        (i32.gt_u
          (local.tee $count (i32.add (local.get $count) (i32.const -1)))
          (i32.const 0)
        )
      )
    )
    (local.get $result)
  )
  
  ;; 
  ;; Determine if a number is an Armstrong number.
  ;;
  ;; @param {i32} candidate - The number to check.
  ;;
  ;; @return {i32} 1 if the number is an Armstrong number, 0 otherwise.
  ;;
  (func (export "isArmstrongNumber") (param $candidate i32) (result i32)
    (local $power i32)
    (local $total i32)
    (local $digit i32)
    (local $remaining i32)
    
    (local.set $remaining (local.get $candidate))
    (local.set $power (call $numberLength (local.get $candidate)))

    (loop $loop
      (local.set $total
        (i32.add
          (local.get $total)
          (call $pow 
            (local.tee $digit (i32.rem_u (local.get $remaining) (i32.const 10)))
            (local.get $power)
          )
        )
      )
      
      (br_if $loop
        (i32.ne
          (i32.const 0)
          (local.tee $remaining (i32.div_u (local.get $remaining) (i32.const 10)))
        )
      )
    )
    
    (i32.eq (local.get $candidate) (local.get $total))
  )
)

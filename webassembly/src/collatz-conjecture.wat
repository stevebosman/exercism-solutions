(module
  (func $half_n (param $number i32) (result i32)
    (return
      (i32.shr_u (local.get $number) (i32.const 1))
    )
  )

  (func $three_n_plus_1 (param $number i32) (result i32)
    (return
      (i32.add
        (i32.mul (i32.const 3) (local.get $number))
        (i32.const 1)
      )
    )
  )

  (func (export "steps") (param $number i32) (result i32)
    (local $count i32)
    (if
      (i32.le_s 
        (local.get $number)
        (i32.const 0)
      )
      (return (i32.const -1))
    )

    (local.set $count (i32.const 0))
    (if
      (i32.le_s 
        (local.get $number)
        (i32.const 1)
      )
      (return (i32.const 0))
    )

    (loop $my_loop
      (if
        (i32.eqz
          (i32.rem_u
            (local.get $number)
            (i32.const 2)
          )
        )
        (then
          (local.set $number
            (call $half_n (local.get $number))
          )
        )
        (else
          (local.set $number
            (call $three_n_plus_1 (local.get $number))
          )
        )
      )

      (local.set $count
        (i32.add
          (local.get $count)
          (i32.const 1)
        )
      )
      
      (br_if $my_loop
        (i32.ne 
          (local.get $number)
          (i32.const 1)
        )
      )
    )
    
    (return (local.get $count))
  )
)
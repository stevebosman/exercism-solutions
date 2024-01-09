(module
  (func (export "eggCount") (param $number i32) (result i32)
    (local $count i32)
    (local.set $count
      (i32.const 0)
    )

    (loop $my_loop
      (if
        (i32.and 
          (local.get $number)
          (i32.const 1)
        )
        (local.set $count
          (i32.add
            (local.get $count)
            (i32.const 1)
          )
        )
      )

      (br_if $my_loop 
        (i32.lt_s 
          (i32.const 0) 
          (local.tee $number
            (i32.shr_u
              (local.get $number)
              (i32.const 1)
            )
          )
        )
      )
    )

    (return (local.get $count))
  )
)

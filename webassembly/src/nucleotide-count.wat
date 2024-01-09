(module
  (memory (export "mem") 1)

  (global $char_a i32 (i32.const 65))
  (global $char_c i32 (i32.const 67))
  (global $char_g i32 (i32.const 71))
  (global $char_t i32 (i32.const 84))

  (func (export "countNucleotides") (param $offset i32) (param $length i32) (result i32 i32 i32 i32)
    (local $count_a i32)
    (local $count_c i32)
    (local $count_g i32)
    (local $count_t i32)
    (local $pointer i32)
    (local $last i32)
    (local $char i32)

    (local.set $count_a (i32.const 0))
    (local.set $count_c (i32.const 0))
    (local.set $count_g (i32.const 0))
    (local.set $count_t (i32.const 0))

    (if (i32.gt_u (local.get $length) (i32.const 0))
      (then
        ;; start loop at offset
        (local.set $pointer (local.get $offset))
        ;; finish loop at offset + length
        (local.set $last 
          (i32.add (local.get $offset) (local.get $length))
        )

        (loop $count
          (local.set $char (i32.load8_u (local.get $pointer)))
  
          (if (i32.eq (local.get $char) (global.get $char_a)) (then
            (local.set $count_a
              (i32.add (local.get $count_a) (i32.const 1))
            )
          ) (else (if (i32.eq (local.get $char) (global.get $char_c)) (then
            (local.set $count_c
              (i32.add (local.get $count_c) (i32.const 1))
            )
          ) (else (if (i32.eq (local.get $char) (global.get $char_g)) (then
            (local.set $count_g
              (i32.add (local.get $count_g) (i32.const 1))
            )
          ) (else (if (i32.eq (local.get $char) (global.get $char_t)) (then
            (local.set $count_t
              (i32.add (local.get $count_t) (i32.const 1))
            )
          ) (else
            (return 
              (i32.const -1)
              (i32.const -1)
              (i32.const -1)
              (i32.const -1)
            )
          ))))))))
  
          ;; loop on each index 
          (br_if $count 
            (i32.gt_u 
              (local.get $last) 
              (local.tee $pointer (i32.add (local.get $pointer) (i32.const 1)))
            )
          )
        )
      )
    )
    (return 
      (local.get $count_a)
      (local.get $count_c)
      (local.get $count_g)
      (local.get $count_t)
    )
  )
)

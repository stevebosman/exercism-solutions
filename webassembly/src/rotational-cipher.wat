(module
  (import "console" "log_i32_s" (func $log_i32_s (param i32)))
  (memory (export "mem") 1)

  (global $upper_a i32 (i32.const 65))
  (global $upper_z i32 (i32.const 90))
  (global $lower_a i32 (i32.const 97))
  (global $lower_z i32 (i32.const 122))

  (func $rotateChar (param $char i32) (param $shiftKey i32) (param $upper i32) (result i32)
    (local $newChar i32)

    (if
      (i32.gt_u
        (local.tee $newChar
          (i32.add (local.get $char) (local.get $shiftKey))
        )
        (local.get $upper)
      )
      (local.set $newChar
        (i32.add (local.get $newChar) (i32.const -26))
      )
    )
    (return (local.get $newChar))
  )

  (func (export "rotate") (param $textOffset i32) (param $textLength i32) (param $shiftKey i32) (result i32 i32)
    (local $pointer i32)
    (local $last i32)
    (local $char i32)
    (local $upper i32)
    (local $lower i32)

    (if (i32.gt_u (local.get $textLength) (i32.const 0))
      (then
        ;; start loop at offset
        (local.set $pointer (local.get $textOffset))
        ;; finish loop at offset + length
        (local.set $last 
          (i32.add (local.get $textOffset) (local.get $textLength))
        )

        (loop $count
          (local.set $char (i32.load8_u (local.get $pointer)))
          (local.set $upper 
            (i32.and
              (i32.ge_s (local.get $char) (global.get $upper_a))
              (i32.le_s (local.get $char) (global.get $upper_z))
            )
          )
          (local.set $lower 
            (i32.and
              (i32.ge_s (local.get $char) (global.get $lower_a))
              (i32.le_s (local.get $char) (global.get $lower_z))
            )
          )
          (if
            (i32.or 
              (local.get $upper)
              (local.get $lower)
            )
            (if
              (local.get $lower)
              (then
                (i32.store8 (local.get $pointer)
                  (call $rotateChar 
                    (local.get $char) (local.get $shiftKey) (global.get $lower_z)
                  )
                )
              )
              (else
                (i32.store8 (local.get $pointer)
                  (call $rotateChar 
                    (local.get $char) (local.get $shiftKey) (global.get $upper_z)
                  )
                )
              )
            )
          )
  
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
    (return (local.get $textOffset) (local.get $textLength))
  )
)

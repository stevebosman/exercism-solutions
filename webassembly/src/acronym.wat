(module
  (import "console" "log_i32_u" (func $log_i32_u (param i32)))
  (memory (export "mem") 1)

  (global $apostrophe i32 (i32.const 39))
  (global $upper_a i32 (i32.const 65))
  (global $upper_z i32 (i32.const 90))
  (global $lower_a i32 (i32.const 97))
  (global $lower_z i32 (i32.const 122))

  (func $in_range (param $low i32) (param $high i32) (param $value i32) (result i32)
    (return
      (i32.and
        (i32.le_u (local.get $low) (local.get $value))
        (i32.ge_u (local.get $high) (local.get $value))
      )
    )
  )

  (func $is_upper_letter (param $char i32) (result i32)
    (return (call $in_range (global.get $upper_a) (global.get $upper_z) (local.get $char)))
  )

  (func $is_lower_letter (param $char i32) (result i32)
    (return (call $in_range (global.get $lower_a) (global.get $lower_z) (local.get $char)))
  )

  ;;
  ;; Converts a phrase into an acronym
  ;; i.e. "Ruby on Rails" -> "ROR"
  ;;
  ;; @param {i32} offset - offset of phrase in linear memory
  ;; @param {i32} length - length of phrase in linear memory
  ;;
  ;; @return {(i32, i32)} - offset and length of acronym
  ;;
  (func (export "parse") (param $offset i32) (param $length i32) (result i32 i32)
    ;; current character
    (local $char i32)
    ;; current input scan location
    (local $ptr_in i32)
    ;; loop until ptr_in gets here
    (local $last_index i32)
    ;; current output location
    (local $ptr_out i32)
    ;; set when next letter should be returned
    (local $use_next i32)
    ;; store result of is_lower_letter call
    (local $is_lower_letter i32)

    (local.set $last_index 
      (i32.add (local.get $offset) (local.get $length))
    )
    (local.set $ptr_in (local.get $offset))
    (local.set $ptr_out (local.get $offset))
    (local.set $use_next (i32.const 1))

    (loop $loop
      (local.set 
        $char
        (i32.load8_u (local.get $ptr_in))
      )

      (local.set 
        $is_lower_letter
        (call $is_lower_letter (local.get $char))
      )

      (if 
        (i32.or
          (local.get $is_lower_letter)
          (call $is_upper_letter (local.get $char))
        )
        (then
          (if (local.get $use_next)
            (then
              (if (local.get $is_lower_letter)
                (local.set $char (i32.sub (local.get $char) (i32.const 32)))
              )
              (i32.store8 (local.get $ptr_out) (local.get $char))
              (local.set $ptr_out (i32.add (local.get $ptr_out) (i32.const 1)))
              (local.set $use_next (i32.const 0))
            )
          )
        )
        (else
          (if (i32.ne (i32.const 1) (i32.eq (global.get $apostrophe) (local.get $char)))
            (local.set $use_next (i32.const 1))
          )
        )
      )

      (br_if $loop
        (i32.lt_u
          (local.tee $ptr_in (i32.add (local.get $ptr_in) (i32.const 1)))
          (local.get $last_index)
        )
      )
    )

    (return 
      (local.get $offset) (i32.sub (local.get $ptr_out) (local.get $offset))
    )
  )
)

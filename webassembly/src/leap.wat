(module
  ;; Returns 1 if leap year, 0 otherwise
  (func (export "isLeap") (param $year i32) (result i32)
    local.get $year
    i32.const 4
    i32.rem_u
    i32.const 0
    i32.ne
    (if
      (then
        i32.const 0
        return
      )
    )

    local.get $year
    i32.const 100
    i32.rem_u
    i32.const 0
    i32.ne
    (if
      (then
        i32.const 1
        return
      )
    )

    local.get $year
    i32.const 400
    i32.rem_u
    i32.eqz
  )  
)

(module
  (func $distance2 (param $x f32) (param $y f32) (result f32)
    local.get $x
    local.get $x
    f32.mul
    local.get $y
    local.get $y
    f32.mul
    f32.add
  )

  (func (export "score") (param $x f32) (param $y f32) (result i32)
    (local $dsqr f32)
    ;; Calculate square of distance from centre
    local.get $x
    local.get $y
    call $distance2
    local.set $dsqr

    ;; inside centre ring
    local.get $dsqr
    f32.const 1
    f32.le
    (if
      (then
        i32.const 10
        return
      )
    )
  
    ;; inside 5 ring
    local.get $dsqr
    f32.const 25
    f32.le
    (if
      (then
        i32.const 5
        return
      )
    )
  
    ;; inside 10 ring
    local.get $dsqr
    f32.const 100
    f32.le
    (if
      (then
        i32.const 1
        return
      )
    )

    ;; miss
    i32.const 0
    return
  )
)

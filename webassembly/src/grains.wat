(module
  ;; squareNum is signed
  ;; Result is unsigned
  (func $square (export "square") (param $squareNum i32) (result i64)
    ;; out of bounds
    ;; n < 1 => 0
    ;; or n > 64 => 0
    local.get $squareNum
    i32.const 1
    i32.lt_s
    local.get $squareNum
    i32.const 64
    i32.gt_s
    i32.or
    (if 
      (then
        i64.const 0
        return
      )
    )

    ;; 2^(n-1), using bit shifting
    i64.const 1
    local.get $squareNum
    i64.extend_i32_u
    i64.const 1
    i64.sub
    i64.shl
  )

  ;; Result is unsigned
  (func (export "total") (result i64)
    ;; signed -1 is same as unsigned 2^64-1
    i64.const -1
  )
)

(module
  ;; The name prefixed with $ is used to internally refer to functions via the call instruction
  ;; The string in the export instruction is the name of the export made available to the
  ;; embedding environment (in this case, Node.js). This is used by our test runner Jest.
  (func $squareOfSum (export "squareOfSum") (param $max i32) (result i32)
    ;; n * (n + 1) / 2;
    (local $sum i32)
    local.get $max
    i32.const 1
    i32.add
    local.get $max
    i32.mul
    i32.const 1
    i32.shr_u
    local.tee $sum
    local.get $sum
    i32.mul
  )

  (func $sumOfSquares (export "sumOfSquares") (param $max i32) (result i32)
    ;; (n * (n + 1) * (2 * n + 1)) / 6;
    local.get $max
    local.get $max
    i32.const 1
    i32.add
    i32.mul
    local.get $max
    i32.const 1
    i32.shl
    i32.const 1
    i32.add
    i32.mul
    i32.const 6
    i32.div_s
  )

  (func (export "difference") (param $max i32) (result i32)
    local.get $max
    call $squareOfSum 
    local.get $max
    call $sumOfSquares
    i32.sub
  )
)

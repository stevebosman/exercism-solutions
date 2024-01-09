(module
  (func $square (param $n i32) (result i32)
    (i32.mul
      (local.get $n)
      (local.get $n)
    )
  )

  (func $average (param $n1 i32) (param $n2 i32) (result i32)
    (i32.shr_u
      (i32.add
        (local.get $n1)
        (local.get $n2)
      )
      (i32.const 1)
    )
  )

  (func (export "squareRoot") (param $radicand i32) (result i32)
    (local $range_min i32)
    (local $range_max i32)
    (local $next i32)
    (local $square i32)
    (local $countdown i32)
    ;; if can't converge in 32 then it is not solveable
    (local.set $countdown (i32.const 32))
    (local.set $range_max (local.get $radicand))

    (if
      (i32.eq (local.get $radicand) (call $square (local.get $radicand)))
      (return (local.get $radicand))
    )

    (loop $my_loop
      (local.set $next (call $average (local.get $range_min) (local.get $range_max)))

      (local.set $square (call $square (local.get $next)))

      (if
        (i32.lt_u (local.get $radicand) (local.get $square))
        (then (local.set $range_max (local.get $next)))
        (else
          (if
            (i32.gt_u (local.get $radicand) (local.get $square))
            (then (local.set $range_min (local.get $next)))
            (else (return (local.get $next)))
          )
        )
      )

      (br_if $my_loop
        (i32.sub
          (local.get $countdown)
          (i32.const 1)
        )
      )
    )
    (return (i32.const -1))
  )
)

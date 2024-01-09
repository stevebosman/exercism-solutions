(module
  ;; sort 2 numbers
  (func $sort2 (param f32 f32) (result f32 f32)
    (if
      ;; already ordered
      (f32.lt (local.get 0) (local.get 1))
      (return
        (local.get 0)
        (local.get 1)
      )
    )
    (return
      (local.get 1)
      (local.get 0)
    )
  )

  ;; sort 3 numbers
  (func $sort (param f32 f32 f32) (result f32 f32 f32)
    (call $sort2 (local.get 0) (local.get 1))
    (local.set 1)
    (local.set 0)

    (call $sort2 (local.get 1) (local.get 2))
    (local.set 2)
    (local.set 1)

    (call $sort2 (local.get 0) (local.get 1))
    (local.get 2)
  )

  ;; count differences.
  ;; params are assumed to be ordered
  (func $diffCount (param f32 f32 f32) (result i32)
    (local $count i32)
    (local.set $count (i32.const 0))

    (if
      (f32.ne (local.get 0) (local.get 1))
      (local.set $count (i32.add (local.get $count) (i32.const 1)))
    )
    
    (if
      (f32.ne (local.get 1) (local.get 2))
      (local.set $count (i32.add (local.get $count) (i32.const 1)))
    )
    
    (return (local.get $count))
  )

  ;; all parameters are positive and sum of first two is greater than third.
  ;; params are assumed to be ordered
  (func $invalid (param f32 f32 f32) (result i32)
    (if 
      (f32.le (local.get 0) (f32.const 0))
      (return (i32.const 1))
    )
    (if 
      (f32.le (f32.add (local.get 0) (local.get 1)) (local.get 2))
      (return (i32.const 1))
    )
    (return (i32.const 0))
  )

  (func (export "isEquilateral") (param f32 f32 f32) (result i32)
    (call $sort (local.get 0) (local.get 1) (local.get 2))
    (local.set 2)
    (local.set 1)
    (local.set 0)

    (if
      (call $invalid (local.get 0) (local.get 1) (local.get 2))
      (return (i32.const 0))
    )

    (i32.eq
      (call $diffCount (local.get 0) (local.get 1) (local.get 2))
      (i32.const 0)
    )
  )

  (func (export "isIsosceles") (param f32 f32 f32) (result i32)
    (call $sort (local.get 0) (local.get 1) (local.get 2))
    (local.set 2)
    (local.set 1)
    (local.set 0)
    (if
      (call $invalid (local.get 0) (local.get 1) (local.get 2))
      (return (i32.const 0))
    )

    (i32.le_s
      (call $diffCount (local.get 0) (local.get 1) (local.get 2))
      (i32.const 1)
    )
  )

  (func (export "isScalene") (param f32 f32 f32) (result i32)
    (call $sort (local.get 0) (local.get 1) (local.get 2))
    (local.set 2)
    (local.set 1)
    (local.set 0)

    (if
      (call $invalid (local.get 0) (local.get 1) (local.get 2))
      (return (i32.const 0))
    )

    (i32.eq
      (call $diffCount (local.get 0) (local.get 1) (local.get 2))
      (i32.const 2)
    )
  )
)

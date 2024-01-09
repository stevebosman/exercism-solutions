(module
  (memory (export "mem") 1)

  (data (i32.const 100) "black")
  (data (i32.const 110) "brown")
  (data (i32.const 120) "red")
  (data (i32.const 130) "orange")
  (data (i32.const 140) "yellow")
  (data (i32.const 150) "green")
  (data (i32.const 160) "blue")
  (data (i32.const 170) "violet")
  (data (i32.const 180) "grey")
  (data (i32.const 190) "white")
  (data (i32.const 200) "black,brown,red,orange,yellow,green,blue,violet,grey,white")

  ;; Return buffer of comma separated colors
  ;; black,brown,red,orange,yellow,green,blue,violet,grey,white
  (func (export "colors") (result i32 i32)
    (return (i32.const 200) (i32.const 58))
  )

  ;; Called each time a( module is initialized
  ;; Can be used to populate globals similar to a constructor
  (func $initialize)
  (start $initialize)

  ;; Given a valid resistor color, returns the associated value 
  (func (export "colorCode") (param $offset i32) (param $len i32) (result i32)
    (local $value i32)
    (local $code_offset i32)
    (local.set $value
      (i32.const 0)
    )
    (local.set $code_offset
      (i32.const 100)
    )
    (loop $my_loop
      ;; check the first four characters match, assume the rest are okay    
      (if
        (i32.eq 
          (i32.load (local.get $code_offset))      
          (i32.load (local.get $offset))      
        )
        (return (local.get $value))
      )
      ;; next code offset
      (local.set $code_offset
        (i32.add
          (local.get $code_offset)
          (i32.const 10)
        )
      )
      ;; try again if next value < 10
      (br_if $my_loop 
        (i32.gt_s 
          (i32.const 10) 
          (local.tee $value
            (i32.add
              (local.get $value)
              (i32.const 1)
            )
          )
        )
      )
    )
    (return (i32.const -1))
  )
)

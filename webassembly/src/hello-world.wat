(module
  (memory (export "mem") 1)

  ;; Initializes the WebAssembly Linear Memory with a UTF-8 string of 13 characters starting at offset 64
  (data (i32.const 64) "Hello, World!")
  
  ;; Returns the base offset and length of the greeting
  ;; The final number (now “13”) must match the length of the new string.
  (func (export "hello") (result i32 i32)
    (i32.const 64) (i32.const 13)
  )
)

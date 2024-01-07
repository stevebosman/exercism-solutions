(defpackage :all-your-base
  (:use :cl)
  (:export :rebase))

(in-package :all-your-base)

(defun rebase (list-digits in-base out-base)
  ; validate bases
  (when (< in-base 2)
    (return-from rebase nil))
  (when (< out-base 2) 
    (return-from rebase nil))

  (let ((i (list-length list-digits)) 
        (total 0) 
        (result ()) 
        (rem 0))
    ; convert list to a single number
    (loop for dig in list-digits do 
      ; validate digit
      (when (< dig 0) (return-from rebase nil))
      (when (>= dig in-base) (return-from rebase nil))

      (setq i (- i 1))
      (setq total (+ total (* dig (expt in-base i)))))

    (when (= total 0) (return-from rebase '(0)))
     
    ; convert number to output list
    (loop while (> total 0) do 
      (setq rem (mod total out-base))
      (setq result (cons rem result))
      (setq total (/ (- total rem) out-base)))
       
    result))

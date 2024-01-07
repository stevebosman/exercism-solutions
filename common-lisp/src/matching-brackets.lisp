(defpackage :matching-brackets
  (:use :cl)
  (:export :pairedp))

(in-package :matching-brackets)

(defun pairedp (value)
  (let ((openers ()))
    (loop for char across value do
      (when (or (string= char "[") (string= char "{") (string= char "(")) 
        (setq openers (cons char openers)))

      (when (string= char "]")
        (unless (string= "[" (car openers))
          (return-from pairedp nil))
        (setq openers (cdr openers)))

      (when (string= char "}")
        (unless (string= "{" (car openers))
          (return-from pairedp nil))
        (setq openers (cdr openers)))

      (when (string= char ")")
        (unless (string= "(" (car openers))
          (return-from pairedp nil))
        (setq openers (cdr openers)))

    )
    (null openers))
)

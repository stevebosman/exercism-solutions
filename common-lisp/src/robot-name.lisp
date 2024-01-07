(defpackage :robot-name
  (:use :cl)
  (:export :build-robot :robot-name :reset-name))

(in-package :robot-name)

(defparameter +alphabet+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(defparameter +digits+ "0123456789")
(defparameter +name-list+ (list))

(defun rnd-letter ()
  (let* ((i (random 26)))
    (subseq +alphabet+ i (1+ i))))

(defun rnd-digit ()
  (let* ((i (random 10)))
    (subseq +digits+ i (1+ i))))

(defun rnd-name ()
  (concatenate 'string (rnd-letter) (rnd-letter) (rnd-digit) (rnd-digit) (rnd-digit)))

(defun unique-name ()
  (let* ((name (rnd-name)))
    (if (member name +name-list+) (unique-name) name)))

(defun build-robot ()
  (list (unique-name)))

(defun robot-name (robot)
  (car robot))

(defun reset-name (robot)
  (let* ((new-name (unique-name)) (old-name (first robot)))
    (setf (first robot) new-name)
    (remove old-name +name-list+)))

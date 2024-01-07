module bob
  implicit none
contains

  function hasNonWhitespace(statement)
    logical :: hasNonWhitespace
    character(len=*), intent(in) :: statement
    character(1) :: c
    integer :: j

    hasNonWhitespace = .false.
    do j=1,len(statement)
      c = statement(j:j)
      if (c /= ' ') then
        hasNonWhitespace = .true.
        exit
      end if
    end do
  end function hasNonWhitespace

  function hasLower(statement)
    logical :: hasLower
    character(len=*), intent(in) :: statement
    character(1) :: c
    integer :: j

    hasLower = .false.
    do j=1,len(statement)
      c = statement(j:j)
      if ('a' <= c .and. c <= 'z') then
        hasLower = .true.
        exit
      end if
    end do
  end function hasLower

  function hasUpper(statement)
    logical :: hasUpper
    character(len=*), intent(in) :: statement
    character(1) :: c
    integer :: j

    hasUpper = .false.
    do j=1,len(statement)
      c = statement(j:j)
      if ('A' <= c .and. c <= 'Z') then
        hasUpper = .true.
        exit
      end if
    end do
  end function hasUpper

  function hey(statement)
    character(100) :: hey
    character(len=*), intent(in) :: statement
    integer :: l
    logical :: question
    logical :: shout

    if (.not. hasNonWhitespace(statement)) then
      hey = "Fine. Be that way!"
    else
      l = len(trim(statement))
      question = (statement(l:l) == "?")
      shout = (.not. hasLower(statement) .and. hasUpper(statement))
      if (shout .and. question) then
        hey = "Calm down, I know what I'm doing!"
      else if (shout) then
        hey = "Whoa, chill out!"
      else if (question) then
        hey = "Sure."
      else
        hey = "Whatever."
      end if
    end if
  end function hey

end module bob

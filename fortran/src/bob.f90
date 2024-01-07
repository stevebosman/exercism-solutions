module bob
  implicit none
contains

  function countNonWhitespace(statement)
    integer :: countNonWhitespace
    character(len=*), intent(in) :: statement
    integer :: j

    countNonWhitespace = 0
    do j = 1, len(statement)
      if (statement(j:j) .ne. " ") then
        countNonWhitespace = countNonWhitespace + 1
      end if
    end do
  end function countNonWhitespace

  function countLower(statement)
    integer :: countLower
    character(len=*), intent(in) :: statement
    integer :: j

    countLower = 0
    do j = 1, len(statement)
      select case(statement(j:j))
        case("a":"z")
          countLower = countLower + 1
      end select
    end do
  end function countLower

 function countUpper(statement)
    integer :: countUpper
    character(len=*), intent(in) :: statement
    integer :: j

    countUpper = 0
    do j = 1, len(statement)
      select case(statement(j:j))
        case("A":"Z")
          countUpper = countUpper + 1
      end select
    end do
  end function countUpper

  function hey(statement)
    character(100) :: hey
    character(len=*), intent(in) :: statement
    integer :: l
    logical :: question
    logical :: shout

    if (countNonWhitespace(statement) == 0) then
      hey = "Fine. Be that way!"
    else
      l = len(trim(statement))
      question = (statement(l:l) == "?")
      shout = (countLower(statement) == 0 .and. countUpper(statement) > 0)
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
module reverse_string
  implicit none
contains

  function reverse(input) result(reversed)
    character(*), intent(in) :: input
    character(len=len(input)) :: reversed
    integer :: i, j, length

    length = len(input)

    forall(i = 1:length, j = length - i + 1)
      reversed(i:i) = input(j:j)
    end forall
  end function

end module


module high_scores
  implicit none
contains

function scores(score_list)
  integer, dimension(:), intent(IN) :: score_list
  integer, dimension(size(score_list)) :: scores
  scores = score_list
end function

integer function latest(score_list)
  integer, dimension(:), intent(IN) :: score_list

  latest = score_list(size(score_list))
end function

integer function personalBest(score_list)
  integer, dimension(:), intent(IN) :: score_list
  personalBest = maxval(score_list)
end function

function personalTopThree(score_list)
  integer, dimension(:), intent(IN) :: score_list
  integer, dimension(3) :: personalTopThree
  logical :: mask(size(score_list))
  integer :: i, l

  personalTopThree = 0
  mask = .true.
  do i = 1, min(3, size(score_list))
    l = maxloc(score_list, dim=1, mask=mask)
    personalTopThree(i) = score_list(l)
    mask(l) = .false.
  end do
end function

end module

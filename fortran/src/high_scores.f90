
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
  integer, dimension(size(score_list)) :: scores
  integer, dimension(3) :: personalTopThree
  integer :: i, l, sz

  scores = score_list
  sz = size(scores)

  ! only after the top 3 so the performance doesn't matter too much
  do i = 1, min(3, sz)
    l = MAXLOC(scores, DIM=1)
    personalTopThree(i) = scores(l)
    scores(l) = -9999
  end do

  ! fill in the rest of the array
  do i = min(3, sz) + 1, 3
    personalTopThree(i) = 0
  end do

end function

end module
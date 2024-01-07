module hamming
  implicit none
contains

  function compute(strand1, strand2, distance)
      character(*) :: strand1, strand2
      integer :: distance, len1, len2, i
      logical :: compute

      distance = 0

      len1 = len(strand1)
      len2 = len(strand2)
      compute = len1 == len2
      if (compute) then
        do i = 1, len1
          if (strand1(i:i) /= strand2(i:i)) then
            distance = distance + 1
          end if
        end do
      end if

  end function compute

end module hamming

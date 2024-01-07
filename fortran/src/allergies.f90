
module allergies
  implicit none

contains
  logical function hasBit(bit, allergy_key)
    integer, intent(in) :: bit
    integer, intent(in) :: allergy_key
    hasBit = (iand(bit, allergy_key) == bit)
  end function

  logical function allergicTo(allergy_str, allergy_key)
    character(len=*), intent(in) :: allergy_str
    integer, intent(in) :: allergy_key
    allergicTo = .false.
    if (allergy_str == "eggs") then
      allergicTo = hasBit(1, allergy_key)
    else if (allergy_str == "peanuts") then
      allergicTo = hasBit(2, allergy_key)
    else if (allergy_str == "shellfish") then
      allergicTo = hasBit(4, allergy_key)
    else if (allergy_str == "strawberries") then
      allergicTo = hasBit(8, allergy_key)
    else if (allergy_str == "tomatoes") then
      allergicTo = hasBit(16, allergy_key)
    else if (allergy_str == "chocolate") then
      allergicTo = hasBit(32, allergy_key)
    else if (allergy_str == "pollen") then
      allergicTo = hasBit(64, allergy_key)
    else if (allergy_str == "cats") then
      allergicTo = hasBit(128, allergy_key)
    end if
  end function

  function appendAllergy(allergicList, allergy)
    character(len=*), intent(in) :: allergicList
    character(len=*), intent(in) :: allergy
    character(len=100) :: appendAllergy

    if (allergicList == ' ') then
      appendAllergy = allergy
    else
      appendAllergy = trim(allergicList) // ' ' // allergy
    end if
  end function

  function allergicList(allergy_key)
    integer, intent(in) :: allergy_key
    character(len=100) :: allergicList
    allergicList = ' '
    if (hasBit(1, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'eggs')
    end if
    if (hasBit(2, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'peanuts')
    end if
    if (hasBit(4, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'shellfish')
    end if
    if (hasBit(8, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'strawberries')
    end if
    if (hasBit(16, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'tomatoes')
    end if
    if (hasBit(32, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'chocolate')
    end if
    if (hasBit(64, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'pollen')
    end if
    if (hasBit(128, allergy_key)) then
      allergicList = appendAllergy(allergicList, 'cats')
    end if
  end function

end module

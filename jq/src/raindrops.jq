def pling:
  if . % 3 == 0 then "Pling" else null end
;
def plang:
  if . % 5 == 0 then "Plang" else null end
;
def plong:
  if . % 7 == 0 then "Plong" else null end
;

.number | pling + plang + plong // .
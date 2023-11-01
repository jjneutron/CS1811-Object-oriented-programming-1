i = 0
A = [1, 2, 14, 3, 23, 6, 7, 8, 99, 10, 11, 12, 13, 4, 5, 69, 73, 1, 5, ]

n = len(A) - 1

max = A[i]

while i != n:
    if A[i] < A[n]:
        i += 1
    else:
        n -= 1
print(A[i])

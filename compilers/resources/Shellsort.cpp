pgm
  int vet[10];
  int n;
  int func shellsort(int v[], int tam){
    
    int i, j, h;
    int gap = 1;
    while(gap < tam){
      gap = 3*gap+1; 
    }
    while(gap > 1){
      gap = gap / 3;
      repeat i from gap to size{
        h = v[i];
        j = i;
        while(j >= gap and h < v[j-gap]){
          vet[j] = v[j-gap];
          j = j-gap;
        }
        v[j] = value;
      }
    }
   return v;

  }
  
  main{
   vet = [2, 3, 4, 0, 9, 7, 8, 1, 5, 6];
   
   int x;
   print(vet);
   vet = shellsort(vet, 10);
   print(vet);
  }
end_pgm
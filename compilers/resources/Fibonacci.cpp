pgm
  func void fibonacci ( int n ) {
    int prev , atual , temp , count ;
    prev = 0 ;
    atual = 1 ;
    count = 0 ;
    
    while ( atual < n ) {
        if ( count == 0 ) {
            print ( prev + ", " ) ;
            count = count + 1 ;
        }
        else {
            print ( atual + ", " ) ;
            temp = prev + atual ;
            prev = atual ;
            atual = temp ;
        }
    }
  }
  
  main {
    int n ;
    read ( n ) ;
    fibonacci ( n ) ;
  }
  
end_pgm

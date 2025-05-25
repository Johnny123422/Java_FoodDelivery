public record Driver(String name, String lastName, String phoneNumber) implements user{
    public Driver{
     if( name == null || name.isEmpty()) {
            throw new RuntimeException( "Trebuie sa completati cu un nume!" );
        }
    }
}

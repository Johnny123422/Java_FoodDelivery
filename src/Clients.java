public record Clients(String name, String lastName, String phoneNumber, Adress adress) implements user {
    public Clients{
     if( name == null || name.isEmpty()) {
            throw new RuntimeException( "Trebuie sa completati cu un nume!" );
        }
    }
}
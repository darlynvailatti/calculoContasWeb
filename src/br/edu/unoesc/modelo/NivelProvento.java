package br.edu.unoesc.modelo;

public enum NivelProvento {
	provido("Provido"),
	naoProvido("Não provido"),
	providoParcial("Provido parcial");
	
	private String value;
    private NivelProvento(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

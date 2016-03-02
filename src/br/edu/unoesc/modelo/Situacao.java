package br.edu.unoesc.modelo;

public enum Situacao {
	cancelado("Cancelado"),
	aberto("Aberto"),
	encerrado("Encerrado");
	
	private String value;
    private Situacao(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

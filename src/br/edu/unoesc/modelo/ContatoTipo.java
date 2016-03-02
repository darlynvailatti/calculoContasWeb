package br.edu.unoesc.modelo;

public enum ContatoTipo {
	TelFixo("Telefone Fixo"), 
    TelMovel("Telefone Móvel");
  
    private String value;
    private ContatoTipo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

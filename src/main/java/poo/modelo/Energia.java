package poo.modelo;

import java.util.ArrayList;

public class Energia extends Card {
    private int quantia;

    public Energia(int quantia, String nome, String anId, String anImageId) {
        super(anId, anImageId, nome);
        this.quantia = quantia;
    }

    public int getQuantia() {
        return quantia;
    }

    //adicionar energia no pokemon
    public void setEnergia(Pokemon pokemon, ArrayList<Card> descarte, ArrayList<Card> mao) {
        pokemon.setEnergia(pokemon.getEnergia() + 10);
        for (Card c : mao) {
            if (c instanceof Energia) {
                descarte.add(c);
                mao.remove(c);
            }
        }
    }

    public String toString() {
        return "ENERGIA -- " + getNome();
    }

    //card no arraylist -- pokemon -- o que é melhor fazer o casting ou botar listas do tipo pokemon que tem constante interacao
    //
}

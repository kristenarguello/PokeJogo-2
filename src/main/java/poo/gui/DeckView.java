package poo.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import poo.modelo.Card;
import poo.modelo.CardDeck;
import poo.modelo.Game;
import poo.modelo.GameEvent;
import poo.modelo.GameListener;
//import poo.modelo.GameEvent.Action;
//import poo.modelo.GameEvent.Target;

public class DeckView extends HBox implements CardViewListener, GameListener {
	private int jogador;
	private CardDeck cDeck;
	private Card selectedCard;

	public DeckView(int nroJog) {
		super(4);
		this.setAlignment(Pos.CENTER);

		jogador = nroJog;
		selectedCard = null;

		cDeck = null;// aqui diferente
		if (jogador == 1) {
			cDeck = Game.getInstance().getVerso();
		} else if (jogador == 2) {
			cDeck = Game.getInstance().getVerso();
		} else if (jogador == 3) {
			cDeck = Game.getInstance().getMaoJ1();
		} else if (jogador == 4) {
			cDeck = Game.getInstance().getMaoJ2();
		} else if (jogador == 5) {
			cDeck = Game.getInstance().getAtivoJ1();
		} else if (jogador == 6) {
			cDeck = Game.getInstance().getAtivoJ2();
		} else if (jogador == 7) {
			cDeck = Game.getInstance().getVerso();
		} else if (jogador == 8) {
			cDeck = Game.getInstance().getVerso();
		} else if (jogador == 9) {
			cDeck = Game.getInstance().getBancoJ1();
		} else if (jogador == 10) {
			cDeck = Game.getInstance().getBancoJ2();
		}

		cDeck.addGameListener(this);

		if (jogador == 5 || jogador == 6) {
			CardView cv = new CardView(cDeck.getCards().get(cDeck.getCards().size() - 1));
			cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		} else {
			for (Card card : cDeck.getCards()) {
				CardView cv = new CardView(card);
				cv.setCardViewObserver(this);
				this.getChildren().add(cv);
			}
		}
	}

	private void removeSel() {
		ObservableList<Node> cards = getChildren();
		for (int i = 0; i < cards.size(); i++) {
			CardView cv = (CardView) cards.get(i);
			if (cv.getCard() == selectedCard) {
				getChildren().remove(cv);
				selectedCard = null;
			}
		}
	}

	// private void removeCardView(Card c) {
	// ObservableList<Node> cards = getChildren();
	// for (int i = 0; i < cards.size(); i++) {
	// CardView cv = (CardView) cards.get(i);
	// if (cv.getCard() == c) {
	// getChildren().remove(i);
	// }
	// }
	// }

	private void showDeck() {
		// ObservableList<Node> cards = getChildren();
		// cDeck.addGameListener(this);

		this.getChildren().clear();

		System.out.println("m1.len>" + cDeck.getNumberOfCards());
		for (int i=0; i<cDeck.getCards().size();i++) {
			System.out.println("show>" + cDeck.getBaralho().get(i));
			CardView cv = new CardView(cDeck.getBaralho().get(i));
			cv.setCardViewObserver(this);
			this.getChildren().add(cv);
		}

		// for (Card card : cDeck.getCards()) {
		// System.out.println("show>" + card);
		// CardView cv = new CardView(card);
		// cv.setCardViewObserver(this);
		// this.getChildren().add(cv);
		// }
	}

	@Override
	public void notify(GameEvent event) {
		System.out.println("ev: " + event);
		if (event.getTarget() != GameEvent.Target.DECK) {
			return;
		}
		if (event.getAction() == GameEvent.Action.REMOVESEL) {
			removeSel();
		}
		if (event.getAction() == GameEvent.Action.SHOWTABLE) {
			showDeck();
		}
		if (event.getAction() == GameEvent.Action.ATTACK) {

		}
	}

	@Override
	public void handle(CardViewEvent event) {
		CardView cv = event.getCardView();
		selectedCard = cv.getCard();
		cDeck.setSelectedCard(selectedCard);
		Game.getInstance().play(cDeck);
	}
}
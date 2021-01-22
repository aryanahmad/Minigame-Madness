package com.example.game.FlipCardGame.FlipCardPresenter;

import com.example.game.FlipCardGame.FlipCardModels.FlipCardMainGameModel;
import com.example.game.FlipCardGame.FlipCardModels.FlipCardReplayModel;
import com.example.game.FlipCardGame.FlipCardView.FlipCardGameView;

/**
 * The FlipCardReplayPresenter class responsible for the instant replay
 *
 * @author Gerald, Harbaksh
 */
public class FlipCardReplayPresenter extends FlipCardMainPresenter {

    /**
     * setting the view
     *
     * @param view the Flip Card game view
     */
    public FlipCardReplayPresenter(FlipCardGameView view) {
        this.view = view;
    }

    /**
     * Start the replay
     */
    @Override
    public void startDisplay() {
        FlipCardMainGameModel model = this.view.getCurrGame();
        FlipCardReplayModel replayModel = new FlipCardReplayModel(model.getCardActionList(), model.getTimeActionList(),
                model.getLastStateList(), this);
        replayModel.startDisplay();
    }
}

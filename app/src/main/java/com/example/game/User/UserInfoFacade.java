package com.example.game.User;

import android.content.Context;

public class UserInfoFacade {
    private CurrUser user;
    public UserInfoFacade(Context currContext)
    {
        this.user = new CurrUser(currContext);
    }

    public void setLevel(int level) {
        this.user.setCurrLevel(level);
    }

    public void startMusic()
    {
        this.user.playMusic();
    }

    public void stopMusic()
    {
        this.user.stopMusic();
    }

    public String getSelectedDifficulty()
    {
        return this.user.getDifficultySelected();
    }

    public int getSelectedColor()
    {
        return this.user.getColorSelected();
    }

    public CurrUser getUser()
    {
        return this.user;
    }
}

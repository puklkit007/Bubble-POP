package com.mygdx.bubblepop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	private SpriteBatch  batch;
	private BitmapFont font;
	private GlyphLayout layout;
    private int total_score = 5, blue_point = 5, red_point = 10, green_point = 15;
    private Texture blue_ball,green_ball,red_ball,random_ball;
    private List<Texture> balls;
    private Sprite sprite;


    @Override
	public void create () {
        batch=new SpriteBatch();

        blue_ball = new Texture("blue_ball.jpg");
        red_ball = new Texture("red_ball.jpg");
        green_ball = new Texture("green_ball.png");
        random_ball = new Texture("blue_ball.jpg");

        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(5);

        balls = new ArrayList<Texture>();

        balls.add(blue_ball);
        balls.add(red_ball);
        balls.add(green_ball);

        font = new BitmapFont();
        font.setColor(Color.CORAL);
        font.getData().setScale(5);

        Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        layout = new GlyphLayout(font,"Score: "+Integer.toString(total_score));
        sprite = new Sprite(random_ball,2500,1500);

        batch.begin();
        sprite.draw(batch);
        font.draw(batch,layout,400,400);
        batch.end();
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	    return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {


        if (random_ball == blue_ball) {
            total_score += blue_point;
        }
        else if (random_ball == red_ball) {
            total_score += red_point;
        }
        else if (random_ball == green_ball) {
            total_score += green_point;
        }

        int color = MathUtils.random(0, 2);
        random_ball =  balls.get(color);
        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
	
	@Override
	public void dispose () {
        batch.dispose();
        font.dispose();
        blue_ball.dispose();
        red_ball.dispose();
        green_ball.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

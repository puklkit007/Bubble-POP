package com.mygdx.bubblepop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	private SpriteBatch  batch;
	private BitmapFont font;
	private GlyphLayout layout;
    private int total_score = 5, blue_point = 5, red_point = 10, green_point = 15;
    private Texture blue_ball,green_ball,red_ball,random_ball;
    private List<Texture> balls;
    private OrthographicCamera camera;
    private Array<Rectangle> bubble_ball;
    private long lastbubble;
    private long startTime;
    private Iterator<Rectangle> iter;
    private Music humble;
    private Array<Rectangle> ball;
    private int i=1;


    @Override
	public void create () {
        batch=new SpriteBatch();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        blue_ball = new Texture("colorballs/blueball.png");
        red_ball = new Texture("colorballs/redball.png");
        green_ball = new Texture("colorballs/greenball.png");
        random_ball = new Texture("colorballs/blueball.png");


        humble = Gdx.audio.newMusic(Gdx.files.internal("music/humblekendricklamar.mp3"));

        humble.setLooping(true);
        humble.play();

        font = new BitmapFont();
        font.setColor(Color.BLUE);
        font.getData().setScale(5);

        balls = new ArrayList<Texture>();

        bubble_ball = new Array<Rectangle>();


        balls.add(blue_ball);
        balls.add(red_ball);
        balls.add(green_ball);

        startTime = System.currentTimeMillis();

        spawnRaindrop();

        font = new BitmapFont();
        font.setColor(Color.CORAL);
        font.getData().setScale(5);

        Gdx.input.setInputProcessor(this);


    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();


        for(Rectangle ball: bubble_ball) {
            batch.draw(random_ball, ball.x, ball.y,ball.width,ball.height);
        }
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            iter.remove();
        }


        if(TimeUtils.nanoTime() - lastbubble > 550000000) {
            spawnRaindrop();


        }


        iter = bubble_ball.iterator();
        while(iter.hasNext()) {
            Rectangle drop = iter.next();
            if ((System.currentTimeMillis() - startTime)/1000 > 75) {
                drop.y -= 800 * Gdx.graphics.getDeltaTime();
            }
            else if((System.currentTimeMillis() - startTime)/1000 > 60) {
                drop.y -= 600 * Gdx.graphics.getDeltaTime();
            }
            else if ((System.currentTimeMillis() - startTime)/1000 > 40) {
                drop.y -= 500 * Gdx.graphics.getDeltaTime();
            }
            else if ((System.currentTimeMillis() - startTime)/1000 > 30) {
                drop.y -= 400 * Gdx.graphics.getDeltaTime();
            }
            else if((System.currentTimeMillis() - startTime)/1000 > 20) {
                drop.y -= 300 * Gdx.graphics.getDeltaTime();
            }
            else if((System.currentTimeMillis() - startTime)/1000 > 10) {
                drop.y -= 200 * Gdx.graphics.getDeltaTime();
            }
            else {
                drop.y -= 100 * Gdx.graphics.getDeltaTime();
            }
            if(drop.y + 64 < 0) iter.remove();
        }

    }

    private void spawnRaindrop() {
        Rectangle ball = new Rectangle();
        ball.x = MathUtils.random(0, 800-64);
        ball.y = 480;
        ball.width = 64;
        ball.height = 64;
        bubble_ball.add(ball);
        lastbubble = TimeUtils.nanoTime();
        randomBall();
    }

    private void randomBall() {
        int color = MathUtils.random(0, 2);
        random_ball =  balls.get(color);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	    return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

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
        humble.dispose();

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

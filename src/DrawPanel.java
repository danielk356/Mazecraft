import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;


public class DrawPanel extends JPanel implements KeyListener, MouseListener
{
    private Maze mazes;
    private Player player;
    private ArrayList<int[][]> mazeList;
    private int mazeNum;
    private int count1;
    private int count2;
    private boolean start;
    private Rectangle startButton;

    public DrawPanel()
    {
        setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setBackground(Color.BLACK);
        mazes = new Maze();
        player = new Player();
        mazeList = mazes.getMazes();
        mazeNum = 0;
        count1 = 0;
        count2 = 0;
        start = false;
        startButton = new Rectangle(430, 325, 320, 60);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int x = 20;
        int y = 20;
        g.setColor(Color.BLACK);

        g.drawRect(1030, 130, 100, 500);
        g.setColor(Color.GRAY);
        g.fillRect(1030, 130, 90, 500);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("MAZE 1: ", 1035, 150);

        if (!mazes.isMaze1Win())
        {
            g.drawImage(mazes.getMaze1StarEmpty(), 1055, 160, 40, 40, null);
        }
        else
        {
            g.drawImage(mazes.getMaze1StarFill(), 1055, 160, 40, 40, null);
        }

        g.drawString("MAZE 2: ", 1035, 250);

        if (!mazes.isMaze2Win())
        {
            g.drawImage(mazes.getMaze2StarEmpty(), 1055, 260, 40, 40, null);
        }
        else
        {
            g.drawImage(mazes.getMaze2StarFill(), 1055, 260, 40, 40, null);
        }

        g.drawString("MAZE 3: ", 1035, 350);

        if (!mazes.isMaze3Win())
        {
            g.drawImage(mazes.getMaze3StarEmpty(), 1055, 360, 40, 40, null);
        }
        else
        {
            g.drawImage(mazes.getMaze3StarFill(), 1055, 360, 40, 40, null);
        }

        if (!start)
        {
            g.drawImage(mazes.getMazePath(), 0, 0, 1150, 820, null);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Courier New", Font.BOLD, 70));
            g.drawString("MAZE", 410, 120);
            g.setColor(Color.RED);
            g.drawString("CRAFT", 580, 120);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Courier New", Font.BOLD, 50));
            g.drawString("Start Game", 440, 370);
            g.drawRect((int)startButton.getX(), (int)startButton.getY(), (int)startButton.getWidth(), (int)startButton.getHeight());
        }

        if (!mazes.isMaze1Win() && start)
        {
            for (int r = 0; r < 15; r++)
            {
                for (int c = 0; c < 20; c++)
                {
                    g.drawRect(x, y, 40, 40);

                    if (!playerSight(r, c))
                    {
                        g.fillRect(x, y, 40, 40);
                    }

                    if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 1)
                    {
                        g.drawImage(mazes.getMazeWall(), x, y, 40, 40, null);
                    }

                    else if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 0)
                    {
                        g.drawImage(mazes.getMazePath(), x, y, 40, 40, null);
                    }

                    if (r == player.getYCoordinate() && c == player.getXCoordinate())
                    {
                        g.drawImage(player.getImage(), x, y, 40, 40, null);
                    }

                    if (mazeList.get(mazeNum)[r][c] == 2)
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x, y, 40, 40);
                        g.setColor(Color.BLACK);
                        if (player.getXCoordinate() == c && player.getYCoordinate() == r)
                        {
                            mazes.setMaze1Win(true);
                        }
                    }
                    x += 50;
                }
                x = 20;
                y += 50;
            }
        }

        if ((mazes.isMaze1Win() && !mazes.isMaze2Win()) && start)
        {
            if (count1 == 0)
            {
                mazeNum++;
                player.setXCoordinate(0);
                player.setYCoordinate(0);
                count1++;
            }

            for (int r = 0; r < 15; r++)
            {
                for (int c = 0; c < 20; c++)
                {
                    g.drawRect(x, y, 40, 40);

                    if (!playerSight(r, c))
                    {
                        g.fillRect(x, y, 40, 40);
                    }

                    if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 1)
                    {
                        g.drawImage(mazes.getMazeWall(), x, y, 40, 40, null);
                    }

                    else if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 0)
                    {
                        g.drawImage(mazes.getMazePath(), x, y, 40, 40, null);
                    }

                    if (r == player.getYCoordinate() && c == player.getXCoordinate())
                    {
                        g.drawImage(player.getImage(), x, y, 40, 40, null);
                    }

                    if (mazeList.get(mazeNum)[r][c] == 2)
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x, y, 40, 40);
                        g.setColor(Color.BLACK);
                        if (player.getXCoordinate() == c && player.getYCoordinate() == r)
                        {
                            mazes.setMaze2Win(true);
                        }
                    }
                    x += 50;
                }
                x = 20;
                y += 50;
            }
        }

        if ((mazes.isMaze1Win() && mazes.isMaze2Win()) && (!mazes.isMaze3Win() && start))
        {
            if (count2 == 0)
            {
                mazeNum++;
                player.setXCoordinate(0);
                player.setYCoordinate(0);
                count2++;
            }

            for (int r = 0; r < 15; r++)
            {
                for (int c = 0; c < 20; c++)
                {
                    g.drawRect(x, y, 40, 40);

                    if (!playerSight(r, c))
                    {
                        g.fillRect(x, y, 40, 40);
                    }

                    if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 1)
                    {
                        g.drawImage(mazes.getMazeWall(), x, y, 40, 40, null);
                    }

                    else if (playerSight(r, c) && mazeList.get(mazeNum)[r][c] == 0)
                    {
                        g.drawImage(mazes.getMazePath(), x, y, 40, 40, null);
                    }

                    if (r == player.getYCoordinate() && c == player.getXCoordinate())
                    {
                        g.drawImage(player.getImage(), x, y, 40, 40, null);
                    }

                    if (mazeList.get(mazeNum)[r][c] == 2)
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x, y, 40, 40);
                        g.setColor(Color.BLACK);
                        if (player.getXCoordinate() == c && player.getYCoordinate() == r)
                        {
                            mazes.setMaze3Win(true);
                        }
                    }
                    x += 50;
                }
                x = 20;
                y += 50;
            }
        }
    }

    private boolean playerSight(int r, int c)
    {
        String currentPoint = r + "," + c;

        ArrayList<String> playerSights = new ArrayList<>();
        playerSights.add((player.getYCoordinate() - 1) + "," + (player.getXCoordinate() - 1));
        playerSights.add((player.getYCoordinate() - 1) + "," + (player.getXCoordinate()));
        playerSights.add((player.getYCoordinate() - 1) + "," + (player.getXCoordinate() + 1));
        playerSights.add((player.getYCoordinate()) + "," + (player.getXCoordinate() - 1));
        playerSights.add((player.getYCoordinate()) + "," + (player.getXCoordinate() + 1));
        playerSights.add((player.getYCoordinate() + 1) + "," + (player.getXCoordinate() - 1));
        playerSights.add((player.getYCoordinate() + 1) + "," + (player.getXCoordinate()));
        playerSights.add((player.getYCoordinate() + 1) + "," + (player.getXCoordinate() + 1));

        playerSights.add((player.getYCoordinate() - 2) + "," + (player.getXCoordinate() - 2));
        playerSights.add((player.getYCoordinate() - 2) + "," + (player.getXCoordinate()));
        playerSights.add((player.getYCoordinate() - 2) + "," + (player.getXCoordinate() + 2));
        playerSights.add((player.getYCoordinate()) + "," + (player.getXCoordinate() - 2));
        playerSights.add((player.getYCoordinate()) + "," + (player.getXCoordinate() + 2));
        playerSights.add((player.getYCoordinate() + 2) + "," + (player.getXCoordinate() - 2));
        playerSights.add((player.getYCoordinate() + 2) + "," + (player.getXCoordinate()));
        playerSights.add((player.getYCoordinate() + 2) + "," + (player.getXCoordinate() + 2));

        playerSights.add((player.getYCoordinate() - 1) + "," + (player.getXCoordinate() - 2));
        playerSights.add((player.getYCoordinate() - 2) + "," + (player.getXCoordinate() + 1));
        playerSights.add((player.getYCoordinate() - 1) + "," + (player.getXCoordinate() + 2));
        playerSights.add((player.getYCoordinate() + 2) + "," + (player.getXCoordinate() + 1));
        playerSights.add((player.getYCoordinate() - 2) + "," + (player.getXCoordinate() - 1));
        playerSights.add((player.getYCoordinate() + 1) + "," + (player.getXCoordinate() - 2));
        playerSights.add((player.getYCoordinate() + 2) + "," + (player.getXCoordinate() - 1));
        playerSights.add((player.getYCoordinate() + 1) + "," + (player.getXCoordinate() + 2));

        for (int i = 0; i < playerSights.size(); i++) {
            if (currentPoint.equals(playerSights.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            if (player.getXCoordinate() != 0 && mazeList.get(mazeNum)[player.getYCoordinate()][player.getXCoordinate() - 1] != 1)
            {
                player.setXCoordinate(player.getXCoordinate() - 1);

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            if (player.getXCoordinate() != mazeList.get(mazeNum)[0].length - 1 && mazeList.get(mazeNum)[player.getYCoordinate()][player.getXCoordinate() + 1] != 1)
            {
                player.setXCoordinate(player.getXCoordinate() + 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            if (player.getYCoordinate() != 0 && mazeList.get(mazeNum)[player.getYCoordinate() - 1][player.getXCoordinate()] != 1)
            {
                player.setYCoordinate(player.getYCoordinate() - 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            if (player.getYCoordinate() != mazeList.get(mazeNum).length - 1 && mazeList.get(mazeNum)[player.getYCoordinate() + 1][player.getXCoordinate()] != 1)
            {
                player.setYCoordinate(player.getYCoordinate() + 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point clicked = e.getPoint();

        if (e.getButton() == 1)
        {
            if (startButton.contains(clicked))
            {
                start = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}



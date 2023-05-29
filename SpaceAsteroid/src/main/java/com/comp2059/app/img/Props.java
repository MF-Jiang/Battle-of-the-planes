package com.comp2059.app.img;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import static com.comp2059.app.controller.GameController.H;
import static com.comp2059.app.controller.GameController.W;
import static com.comp2059.app.props.FireProps.props;

/**
 * The type Props that create Props image .
 * @Title Props
 */
public class Props implements Img{
    /**
     * The Img props that be created.
     */
    Image imgProps = new Image(getClass().getResource("/com/comp2059/app/img/property.png").toExternalForm());
    @Override
    public void creatImg(AnchorPane root) {
        Node newProps = new ImageView(imgProps);
        newProps.relocate((Math.random() * (W)), (Math.random() / (H)));
        props.add(newProps);
        root.getChildren().add(newProps);
    }
}

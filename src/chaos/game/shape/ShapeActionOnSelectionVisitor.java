package chaos.game.shape;

import chaos.game.gui.WindowVisitor;

public interface ShapeActionOnSelectionVisitor {
	
    void accept(WindowVisitor visitor);

}

package net.sourceforge.plantuml.sequencediagram.graphic;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.sequencediagram.InGroupable;
import net.sourceforge.plantuml.sequencediagram.NotePosition;
import net.sourceforge.plantuml.skin.Context2D;
import net.sourceforge.plantuml.ugraphic.UGraphic;

class ArrowAndNoteBox extends Arrow implements InGroupable {

	private final Arrow arrow;
	private final List<NoteBox> noteBoxes = new ArrayList<>();

	public ArrowAndNoteBox(StringBounder stringBounder, Arrow arrow, List<NoteBox> noteBoxes) {
		super(arrow.getStartingY(), arrow.getSkin(), arrow.getArrowComponent(), arrow.getUrl());
		this.arrow = arrow;
		this.noteBoxes.addAll(noteBoxes);

		for (NoteBox noteBox : noteBoxes) {
			final double arrowHeight = arrow.getPreferredHeight(stringBounder);
			final double noteHeight = noteBox.getPreferredHeight(stringBounder);
			final double myHeight = getPreferredHeight(stringBounder);

			final double diffHeightArrow = myHeight - arrowHeight;
			final double diffHeightNote = myHeight - noteHeight;
			if (diffHeightArrow > 0) {
				arrow.pushToDown(diffHeightArrow / 2);
			}
			if (diffHeightNote > 0) {
				noteBox.pushToDown(diffHeightNote / 2);
			}
		}
	}

	@Override
	final public double getArrowOnlyWidth(StringBounder stringBounder) {
		return arrow.getPreferredWidth(stringBounder);
	}

	@Override
	public void setMaxX(double m) {
		super.setMaxX(m);
		arrow.setMaxX(m);
	}

	@Override
	protected void drawInternalU(UGraphic ug, double maxX, Context2D context) {
		arrow.drawU(ug, maxX, context);
		for (NoteBox noteBox : noteBoxes) {
			noteBox.drawU(ug, maxX, context);
		}
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		double result = arrow.getPreferredHeight(stringBounder);
		for (NoteBox noteBox : noteBoxes) {
			result = Math.max(result, noteBox.getPreferredHeight(stringBounder));
		}
		return result;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		double w = arrow.getPreferredWidth(stringBounder);
		w = Math.max(w, arrow.getActualWidth(stringBounder));
		double result = w;
		for (NoteBox noteBox : noteBoxes) {
			result += noteBox.getPreferredWidth(stringBounder);
			if (noteBox.getNotePosition() == NotePosition.RIGHT) {
				result += noteBox.getRightShift(arrow.getStartingY());
			}
		}
		return result;
	}

	@Override
	public double getActualWidth(StringBounder stringBounder) {
		return getPreferredWidth(stringBounder);
	}

	@Override
	public double getStartingX(StringBounder stringBounder) {
		double result = arrow.getStartingX(stringBounder);
		for (NoteBox noteBox : noteBoxes) {
			result = Math.min(result, noteBox.getStartingX(stringBounder));
		}
		return result;
	}

	@Override
	public int getDirection(StringBounder stringBounder) {
		return arrow.getDirection(stringBounder);
	}

	@Override
	public double getArrowYStartLevel(StringBounder stringBounder) {
		return arrow.getArrowYStartLevel(stringBounder);
	}

	@Override
	public double getArrowYEndLevel(StringBounder stringBounder) {
		return arrow.getArrowYEndLevel(stringBounder);
	}

	public double getMaxX(StringBounder stringBounder) {
		return getStartingX(stringBounder) + getPreferredWidth(stringBounder);
	}

	public double getMinX(StringBounder stringBounder) {
		return getStartingX(stringBounder);
	}

	public String toString(StringBounder stringBounder) {
		return toString();
	}

	@Override
	public LivingParticipantBox getParticipantAt(StringBounder stringBounder, NotePosition position) {
		return arrow.getParticipantAt(stringBounder, position);
	}

}

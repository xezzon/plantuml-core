package net.sourceforge.plantuml.nwdiag.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.plantuml.ComponentStyle;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.SymbolContext;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.graphic.USymbols;
import net.sourceforge.plantuml.klimt.color.HColor;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.klimt.geom.HorizontalAlignment;
import net.sourceforge.plantuml.nwdiag.next.NBar;
import net.sourceforge.plantuml.nwdiag.next.NServerDraw;
import net.sourceforge.plantuml.skin.ActorStyle;
import net.sourceforge.plantuml.style.SName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.style.StyleBuilder;
import net.sourceforge.plantuml.style.StyleSignatureBasic;
import net.sourceforge.plantuml.svek.PackageStyle;

public class NServer {

	private final Map<Network, String> connections = new LinkedHashMap<Network, String>();

	private USymbol shape = USymbols.RECTANGLE;
	private final String name;
	private String description;
	private String backcolor;
	private final NBar bar;
	private final ISkinParam skinParam;

	private boolean printFirstLink = true;

	public void doNotPrintFirstLink() {
		this.printFirstLink = false;
	}

	public String someAddress() {
		if (connections.size() > 0)
			return connections.values().iterator().next();
		return "";
	}

	public Network someNetwork() {
		if (connections.size() > 0)
			return connections.keySet().iterator().next();
		return null;
	}

	public void blankSomeAddress() {
		if (connections.size() > 0) {
			final Network it = connections.keySet().iterator().next();
			connections.put(it, "");
		}
	}

	public final boolean printFirstLink() {
		return printFirstLink;
	}

	public Network getMainNetworkNext() {
		return connections.keySet().iterator().next();
	}

	public String getAdress(Network network) {
		return connections.get(network);
	}

	public TextBlock toTextBlock(SName sname, String s) {
		if (s == null)
			return null;

		if (s.length() == 0)
			return TextBlockUtils.empty(0, 0);

		s = s.replace(", ", "\\n");
		return Display.getWithNewlines(s).create(getFontConfiguration(sname), HorizontalAlignment.LEFT, skinParam);
	}

	private StyleSignatureBasic getStyleDefinition(SName sname) {
		return StyleSignatureBasic.of(SName.root, SName.element, SName.nwdiagDiagram, sname);
	}

	private FontConfiguration getFontConfiguration(SName sname) {
		final StyleBuilder styleBuilder = skinParam.getCurrentStyleBuilder();
		final Style style = getStyleDefinition(sname).getMergedStyle(styleBuilder);
		return style.getFontConfiguration(skinParam.getIHtmlColorSet());
	}

	public NServerDraw getDraw(double topMargin, Map<Network, String> conns, List<Network> networks,
			ISkinParam skinParam) {
		final StyleBuilder styleBuilder = skinParam.getCurrentStyleBuilder();
		SymbolContext symbolContext = getStyleDefinition(SName.server).getMergedStyle(styleBuilder)
				.getSymbolContext(skinParam.getIHtmlColorSet());
		if (backcolor != null)
			try {
				final HColor back = skinParam.getIHtmlColorSet().getColor(backcolor);
				symbolContext = symbolContext.withBackColor(back);
			} catch (NoSuchColorException e) {
			}

		final TextBlock desc = toTextBlock(SName.server, getDescription());
		final TextBlock box = getShape().asSmall(TextBlockUtils.empty(0, 0), desc, TextBlockUtils.empty(0, 0),
				symbolContext, HorizontalAlignment.CENTER);
		return new NServerDraw(this, box, conns, networks, topMargin);
	}

	public void connectTo(Network network, String address) {
		if (address == null)
			address = "";
		if (address.length() == 0 && connections.containsKey(network))
			return;

		connections.put(network, address);
		if (bar.getStart() == null)
			bar.addStage(network.getNstage());
		else if (this.getMainNetworkNext() != network)
			bar.addStage(network.getUp());
	}

	public void updateProperties(Map<String, String> props) {
		if (props.get("description") != null)
			this.description = props.get("description");
		this.backcolor = props.get("color");

		final String shape = props.get("shape");
		if (shape != null) {
			final USymbol shapeFromString = USymbols.fromString(shape, ActorStyle.STICKMAN, ComponentStyle.RECTANGLE,
					PackageStyle.RECTANGLE);
			if (shapeFromString != null)
				this.shape = shapeFromString;
		}

	}

	@Override
	public final String toString() {
		return name;
	}

	public static NServer create(String name, ISkinParam skinParam) {
		return new NServer(name, new NBar(), skinParam);
	}

	public NServer(String name, NBar bar, ISkinParam skinParam) {
		this.description = name;
		this.name = name;
		this.bar = bar;
		this.skinParam = skinParam;
	}

	public final String getDescription() {
		return description;
	}

	public final String getName() {
		return name;
	}

	public final USymbol getShape() {
		return shape;
	}

	public final NBar getBar() {
		return bar;
	}

	public final ISkinParam getSkinParam() {
		return skinParam;
	}

}

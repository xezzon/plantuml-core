package net.sourceforge.plantuml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.plantuml.wasm.WasmLog;

import net.sourceforge.plantuml.activitydiagram.ActivityDiagramFactory;
import net.sourceforge.plantuml.activitydiagram3.ActivityDiagramFactory3;
import net.sourceforge.plantuml.api.PSystemFactory;
import net.sourceforge.plantuml.classdiagram.ClassDiagramFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;
import net.sourceforge.plantuml.descdiagram.DescriptionDiagramFactory;
import net.sourceforge.plantuml.ebnf.PSystemEbnfFactory;
import net.sourceforge.plantuml.eggs.PSystemCharlieFactory;
import net.sourceforge.plantuml.eggs.PSystemColorsFactory;
import net.sourceforge.plantuml.eggs.PSystemWelcomeFactory;
import net.sourceforge.plantuml.error.PSystemError;
import net.sourceforge.plantuml.error.PSystemErrorUtils;
import net.sourceforge.plantuml.flowdiagram.FlowDiagramFactory;
import net.sourceforge.plantuml.gitlog.GitDiagramFactory;
import net.sourceforge.plantuml.hcl.HclDiagramFactory;
import net.sourceforge.plantuml.jsondiagram.JsonDiagramFactory;
import net.sourceforge.plantuml.mindmap.MindMapDiagramFactory;
import net.sourceforge.plantuml.nwdiag.NwDiagramFactory;
import net.sourceforge.plantuml.project.GanttDiagramFactory;
import net.sourceforge.plantuml.regex.RegexConcat;
import net.sourceforge.plantuml.regexdiagram.PSystemRegexFactory;
import net.sourceforge.plantuml.salt.PSystemSaltFactory;
import net.sourceforge.plantuml.security.SecurityProfile;
import net.sourceforge.plantuml.security.SecurityUtils;
import net.sourceforge.plantuml.sequencediagram.SequenceDiagramFactory;
import net.sourceforge.plantuml.statediagram.StateDiagramFactory;
import net.sourceforge.plantuml.sudoku.PSystemSudokuFactory;
import net.sourceforge.plantuml.text.StringLocated;
import net.sourceforge.plantuml.timingdiagram.TimingDiagramFactory;
import net.sourceforge.plantuml.utils.Log;
import net.sourceforge.plantuml.version.License;
import net.sourceforge.plantuml.version.PSystemLicenseFactory;
import net.sourceforge.plantuml.version.PSystemVersionFactory;
import net.sourceforge.plantuml.wbs.WBSDiagramFactory;
import net.sourceforge.plantuml.yaml.YamlDiagramFactory;

public class PSystemBuilder {

	public static final long startTime = System.currentTimeMillis();

	final public Diagram createPSystem(List<StringLocated> source, List<StringLocated> rawSource,
			Map<String, String> skinParam) {

		WasmLog.log("..compiling diagram...");

		final long now = System.currentTimeMillis();

		Diagram result = null;
		try {
			final DiagramType type = DiagramType.getTypeFromArobaseStart(source.get(0).getString());
			final UmlSource umlSource = UmlSource.createWithRaw(source, type == DiagramType.UML, rawSource);

			for (StringLocated s : source) {
				if (s.getPreprocessorError() != null) {
					// Dead code : should not append
					assert false;
					Log.error("Preprocessor Error: " + s.getPreprocessorError());
					final ErrorUml err = new ErrorUml(ErrorUmlType.SYNTAX_ERROR, s.getPreprocessorError(), 0,
							s.getLocation());
					return PSystemErrorUtils.buildV2(umlSource, err, Collections.<String>emptyList(), source);
				}
			}

			final DiagramType diagramType = umlSource.getDiagramType();
			final List<PSystemError> errors = new ArrayList<>();
			for (PSystemFactory systemFactory : factories) {
				if (diagramType != systemFactory.getDiagramType())
					continue;

				// WasmLog.log("...trying " + systemFactory.getClass().getName() + " ...");
				final Diagram sys = systemFactory.createSystem(umlSource, skinParam);
				if (isOk(sys)) {
					result = sys;
					return sys;
				}
				errors.add((PSystemError) sys);
			}

			result = PSystemErrorUtils.merge(errors);
			return result;
		} finally {
			WasmLog.log("...parsing ok...");
		}
	}

	private static final List<PSystemFactory> factories = new ArrayList<>();

	static {
		factories.add(new PSystemWelcomeFactory());
		factories.add(new PSystemColorsFactory());
		factories.add(new SequenceDiagramFactory());
		factories.add(new ClassDiagramFactory());
		factories.add(new ActivityDiagramFactory());
		factories.add(new DescriptionDiagramFactory());
		factories.add(new StateDiagramFactory());
		factories.add(new ActivityDiagramFactory3());


		// factories.add(new PostIdDiagramFactory());
		factories.add(new PSystemLicenseFactory());
		factories.add(new PSystemVersionFactory());
		factories.add(new PSystemSaltFactory(DiagramType.UML));
		factories.add(new PSystemSaltFactory(DiagramType.SALT));
		factories.add(new NwDiagramFactory(DiagramType.UML));
		factories.add(new NwDiagramFactory(DiagramType.NW));
		factories.add(new MindMapDiagramFactory());
		factories.add(new WBSDiagramFactory());
		if (License.getCurrent() == License.GPL || License.getCurrent() == License.GPLV2) {
			// factories.add(new PSystemLogoFactory());
			factories.add(new PSystemSudokuFactory());
		}
		factories.add(new PSystemCharlieFactory());
		factories.add(new GanttDiagramFactory());
		factories.add(new FlowDiagramFactory());
		// factories.add(new PSystemTreeFactory(DiagramType.JUNGLE));
		// factories.add(new PSystemCuteFactory(DiagramType.CUTE));
		factories.add(new TimingDiagramFactory());
		factories.add(new JsonDiagramFactory());
		factories.add(new GitDiagramFactory());
		factories.add(new YamlDiagramFactory());
		factories.add(new HclDiagramFactory());
		factories.add(new PSystemEbnfFactory());
		factories.add(new PSystemRegexFactory());
	}

	private boolean isOk(Diagram ps) {
		if (ps == null || ps instanceof PSystemError)
			return false;

		return true;
	}

}

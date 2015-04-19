package com.jayanslow.qlabMasker.painters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;
import com.jayanslow.qlabMasker.models.Workspace;

public class ScreenEditGLPainter extends AbstractScreenGLPainter {

  private static final ReadableColor CLEAR_COLOR = ReadableColor.BLACK;

  private static final ReadableColor BORDER_COLOR = ReadableColor.YELLOW;

  private final Workspace _workspace;

  private final GLPainter<Polygon> _polygonStrokePainter;

  @Inject
  public ScreenEditGLPainter(final GLUtils glUtils, final Workspace workspace, @TargetPainterMode(PainterMode.EDIT) final GLPainter<Polygon> polygonFillPainter, @Named(PaintersModule.NAME_EDIT_STROKE_PAINTER) final GLPainter<Polygon> polygonStrokePainter) {
    super(glUtils, polygonFillPainter, CLEAR_COLOR);
    _workspace = workspace;
    _polygonStrokePainter = polygonStrokePainter;
  }

  @Override
  public void paint(final Screen screen) {
    paintScreenStroke(screen);
    super.paint(screen);
    final List<Polygon> polygons = screen.getPolygons();

    paintPolygonsStroke(polygons);

    final Stream<Point> points = polygons.stream().flatMap(polygon -> polygon.getPoints().stream());

  }

  private void paintPolygonsStroke(final List<Polygon> polygons) {
    final Optional<Polygon> selectedPolygon = _workspace.getSelectedPolygon();
    for (final Polygon polygon : polygons) {
      if (!selectedPolygon.map(p -> p.equals(polygon)).orElse(false)) {
        _polygonStrokePainter.paint(polygon);
      }
    }
    selectedPolygon.ifPresent(p -> _polygonStrokePainter.paint(p));
  }

  private void paintScreenStroke(final Screen screen) {
    glUtils().applyColor(BORDER_COLOR);
    glUtils().setPolygonMode(false);
    GL11.glRectf(0.5f, 0.5f, screen.getWidth() - 0.5f, screen.getHeight() - 0.5f);
  }
}

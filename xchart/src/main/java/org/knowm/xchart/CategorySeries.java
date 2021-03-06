/**
 * Copyright 2015-2016 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.knowm.xchart;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.knowm.xchart.internal.Series_Markers;
import org.knowm.xchart.internal.chartpart.Axis.AxisDataType;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.chartpart.RenderableSeries.LegendRenderType;

/**
 * A Series containing category data to be plotted on a Chart
 *
 * @author timmolter
 */
public class CategorySeries extends Series_Markers {

  public enum CategorySeriesRenderStyle implements RenderableSeries {

    Line(LegendRenderType.Line),

    Area(LegendRenderType.Line),

    Scatter(LegendRenderType.Scatter),

    SteppedBar(LegendRenderType.Box),
    
    Bar(LegendRenderType.Box),

    Stick(LegendRenderType.Line);

    private final LegendRenderType legendRenderType;

    private CategorySeriesRenderStyle(LegendRenderType legendRenderType) {
      this.legendRenderType = legendRenderType;
    }

    @Override
    public LegendRenderType getLegendRenderType() {

      return legendRenderType;
    }
  }

  private CategorySeriesRenderStyle chartCategorySeriesRenderStyle = null;

  /**
   * Constructor
   *
   * @param name
   * @param xData
   * @param yData
   * @param errorBars
   */
  public CategorySeries(String name, List<?> xData, List<? extends Number> yData, List<? extends Number> errorBars) {

    super(name, xData, yData, errorBars);
  }

  public CategorySeriesRenderStyle getChartCategorySeriesRenderStyle() {

    return chartCategorySeriesRenderStyle;
  }

  public CategorySeries setChartCategorySeriesRenderStyle(CategorySeriesRenderStyle chartXYSeriesRenderStyle) {

    this.chartCategorySeriesRenderStyle = chartXYSeriesRenderStyle;
    return this;
  }

  @Override
  public LegendRenderType getLegendRenderType() {

    return chartCategorySeriesRenderStyle.getLegendRenderType();
  }

  @Override
  public AxisDataType getAxesType(List<?> data) {

    AxisDataType axisType;

    Iterator<?> itr = data.iterator();
    Object dataPoint = itr.next();
    if (dataPoint instanceof Number) {
      axisType = AxisDataType.Number;
    }
    else if (dataPoint instanceof Date) {
      axisType = AxisDataType.Date;
    }
    else if (dataPoint instanceof String) {
      axisType = AxisDataType.String;
    }
    else {
      throw new IllegalArgumentException("Series data must be either Number, Date or String type!!!");
    }
    return axisType;
  }
}

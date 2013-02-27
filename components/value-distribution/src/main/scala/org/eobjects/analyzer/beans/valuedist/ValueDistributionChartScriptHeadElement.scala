package org.eobjects.analyzer.beans.valuedist
import scala.collection.JavaConversions.collectionAsScalaIterable
import org.eobjects.analyzer.result.html.HeadElement
import org.eobjects.analyzer.result.html.HtmlRenderingContext
import org.eobjects.analyzer.result.ValueCountingAnalyzerResult
import org.eobjects.analyzer.util.LabelUtils
import org.eobjects.analyzer.result.ValueCount

class ValueDistributionChartScriptHeadElement(result: ValueCountingAnalyzerResult, chartElementId: String) extends HeadElement {

  override def toHtml(context: HtmlRenderingContext): String = {
    val valueCounts = result.getValueCounts();

    val unexpectedValueCount = result.getUnexpectedValueCount()
    if (unexpectedValueCount != null && unexpectedValueCount > 0) {
      valueCounts.add(new ValueCount(LabelUtils.UNEXPECTED_LABEL, unexpectedValueCount));
    }

    val uniqueCount = result.getUniqueCount();
    if (uniqueCount != null && uniqueCount > 0) {
      val vc = new ValueCount(LabelUtils.UNIQUE_LABEL, uniqueCount);
      valueCounts.add(vc);
      val displayCount: String = vc.getCount().toString();
      val displayText: String = vc.getValue();
    }

    return """
<script type="text/javascript">
jQuery(function () {	
	var series = Math.floor(Math.random()*10)+1;
     var elem = document.getElementById("""" + chartElementId + """");
       var data = [
     """ +
      valueCounts.map(vc => {
        "{label:\"" + context.escapeJson(LabelUtils.getValueLabel(vc.getValue())) + "\", " + "data:" + +vc.getCount() + "}" + "";
      }).mkString(",") + """
     ];
	// INTERACTIVE
    wait_for_script_load('jQuery',function(){ 
     jQuery.plot(elem, data, 
	{
		series: {
            pie: {
                show: true,
                radius: 3/5,         
      }
		},
		grid: {
			hoverable: true,
			clickable: true
		}
	});
	jQuery(elem).bind("plothover", pieHover);
	jQuery(elem).bind("plotclick", pieClick);

})}, 100);
function pieHover(event, pos, obj) 
{
	if (!obj)
                return;
	percent = parseFloat(obj.series.percent).toFixed(2);
	jQuery("#hover").html('<span style="font-weight: bold; color: '+obj.series.color+'">'+obj.series.label+' ('+percent+'%)</span>');
}

function pieClick(event, pos, obj) 
{
	if (!obj)
                return;
	percent = parseFloat(obj.series.percent).toFixed(2);
	alert(''+obj.series.label+': '+percent+'%');
}
     -->
</script>
	<style type="text/css">
		* {
		  font-family: sans-serif;
		}
		
		div.graph
		{
			width: 400px;
			height: 300px;
			float: left;
			border: 1px dashed gainsboro;
		}
      			</style> 
"""
  }
}
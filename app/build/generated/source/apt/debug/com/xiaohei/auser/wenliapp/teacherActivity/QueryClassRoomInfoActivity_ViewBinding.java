// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.teacherActivity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QueryClassRoomInfoActivity_ViewBinding implements Unbinder {
  private QueryClassRoomInfoActivity target;

  @UiThread
  public QueryClassRoomInfoActivity_ViewBinding(QueryClassRoomInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QueryClassRoomInfoActivity_ViewBinding(QueryClassRoomInfoActivity target, View source) {
    this.target = target;

    target.layout = Utils.findRequiredViewAsType(source, R.id.rl_layout, "field 'layout'", LinearLayout.class);
    target.ly_return = Utils.findRequiredViewAsType(source, R.id.ly_return, "field 'ly_return'", RelativeLayout.class);
    target.ly_to = Utils.findRequiredViewAsType(source, R.id.ly_to, "field 'ly_to'", LinearLayout.class);
    target.v_empty = Utils.findRequiredView(source, R.id.la_empty, "field 'v_empty'");
    target.listView = Utils.findRequiredViewAsType(source, R.id.stuinfolist, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QueryClassRoomInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layout = null;
    target.ly_return = null;
    target.ly_to = null;
    target.v_empty = null;
    target.listView = null;
  }
}

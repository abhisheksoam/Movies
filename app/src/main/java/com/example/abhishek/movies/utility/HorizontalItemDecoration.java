package com.example.abhishek.movies.utility;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhishek on 26/11/16.
 */

public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {

        private final int horizontalSpaceHeight;

        public HorizontalItemDecoration(int verticalSpaceHeight) {
            this.horizontalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.right = horizontalSpaceHeight;
        }
}


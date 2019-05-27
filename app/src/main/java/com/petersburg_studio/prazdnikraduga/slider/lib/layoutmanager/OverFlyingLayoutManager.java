package com.petersburg_studio.prazdnikraduga.slider.lib.layoutmanager;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class OverFlyingLayoutManager extends RecyclerView.LayoutManager
        implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private float minScale = 0.75f;
    private float angle = 8f;
    private int itemSpace = 385;
    private boolean mInfinite = true;
    public static final int DETERMINE_BY_MAX_AND_MIN = -1;

    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;

    public static final int VERTICAL = OrientationHelper.VERTICAL;

    protected int mDecoratedMeasurement;

    protected int mDecoratedMeasurementInOther;

    public float getMinScale() {
        return minScale;
    }

    public void setMinScale(float minScale) {
        this.minScale = minScale;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getItemSpace() {
        return itemSpace;
    }

    public void setItemSpace(int itemSpace) {
        this.itemSpace = itemSpace;
    }

    int mOrientation;

    protected int mSpaceMain;

    protected int mSpaceInOther;

    protected float mOffset;

    protected OrientationHelper mOrientationHelper;

    private boolean mReverseLayout = false;

    private boolean mSmoothScrollbarEnabled = true;

    private int mPendingScrollPosition = RecyclerView.NO_POSITION;

    private SavedState mPendingSavedState = null;

    protected float mInterval;

    /* package */ OnPageChangeListener onPageChangeListener;

    private boolean mRecycleChildrenOnDetach;


    private boolean mEnableBringCenterToFront;

    private boolean mIntegerDy = false;

    private int mLeftItems;

    private int mRightItems;

    private int mMaxVisibleItemCount = DETERMINE_BY_MAX_AND_MIN;

    protected float setInterval() {
        return mDecoratedMeasurement - itemSpace;
    }

    protected void setItemViewProperty(View itemView, float targetOffset) {
        float scale = calculateScale(targetOffset + mSpaceMain);
        itemView.setScaleX(scale);
        itemView.setScaleY(scale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemView.setElevation(0);
        }
        final float rotation = calRotation(targetOffset);
        if (getOrientation() == HORIZONTAL) {
            itemView.setRotationY(rotation);
        } else {
            itemView.setRotationX(-rotation);
        }
    }

    private float calRotation(float targetOffset) {
        return -angle / mInterval * targetOffset;
    }

    private float calculateScale(float x) {
        float deltaX = Math.abs(x - (mOrientationHelper.getTotalSpace() - mDecoratedMeasurement) / 2f);
        return (minScale - 1) * deltaX / (mOrientationHelper.getTotalSpace() / 2f) + 1f;
    }

    protected float setViewElevation(View itemView, float targetOffset) {
        return itemView.getScaleX() * 5;
    }

    public OverFlyingLayoutManager(Context context) {
        this(HORIZONTAL, false);
    }

    public OverFlyingLayoutManager(int orientation, boolean reverseLayout) {
        setOrientation(orientation);
        setReverseLayout(reverseLayout);
        setAutoMeasureEnabled(true);
        setEnableBringCenterToFront(true);
        setIntegerDy(true);
    }

    public OverFlyingLayoutManager(float minScale, int itemSpace, int orientation) {
        this(orientation, false);
        this.minScale = minScale;
        this.itemSpace = itemSpace;
        mOrientation = orientation;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public boolean getRecycleChildrenOnDetach() {
        return mRecycleChildrenOnDetach;
    }

    public void setRecycleChildrenOnDetach(boolean recycleChildrenOnDetach) {
        mRecycleChildrenOnDetach = recycleChildrenOnDetach;
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        if (mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (mPendingSavedState != null) {
            return new SavedState(mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.position = mPendingScrollPosition;
        savedState.offset = mOffset;
        savedState.isReverseLayout = mReverseLayout;
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            mPendingSavedState = new SavedState((SavedState) state);
            requestLayout();
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return mOrientation == HORIZONTAL;
    }

    @Override
    public boolean canScrollVertically() {
        return mOrientation == VERTICAL;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation:" + orientation);
        }
        assertNotInLayoutOrScroll(null);
        if (orientation == mOrientation) {
            return;
        }
        mOrientation = orientation;
        mOrientationHelper = null;
        removeAllViews();
    }

    public int getMaxVisibleItemCount() {
        return mMaxVisibleItemCount;
    }

    public void setMaxVisibleItemCount(int mMaxVisibleItemCount) {
        assertNotInLayoutOrScroll(null);
        if (this.mMaxVisibleItemCount == mMaxVisibleItemCount) return;
        this.mMaxVisibleItemCount = mMaxVisibleItemCount;
        removeAllViews();
    }

    public boolean isIntegerDy() {
        return mIntegerDy;
    }

    public void setIntegerDy(boolean mIntegerDy) {
        this.mIntegerDy = mIntegerDy;
    }

    private void resolveShouldLayoutReverse() {
        if (mOrientation == HORIZONTAL && getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL) {
            mReverseLayout = !mReverseLayout;
        }
    }

    public boolean getReverseLayout() {
        return mReverseLayout;
    }

    public void setReverseLayout(boolean reverseLayout) {
        assertNotInLayoutOrScroll(null);
        if (reverseLayout == mReverseLayout) {
            return;
        }
        mReverseLayout = reverseLayout;
        removeAllViews();
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public PointF computeScrollVectorForPosition(int targetPosition) {
        if (getChildCount() == 0) {
            return null;
        }
        final int firstChildPos = getPosition(getChildAt(0));
        final float direction = targetPosition < firstChildPos == !mReverseLayout ?
                -1 / getDistanceRatio() : 1 / getDistanceRatio();
        if (mOrientation == HORIZONTAL) {
            return new PointF(direction, 0);
        } else {
            return new PointF(0, direction);
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            mOffset = 0;
            return;
        }

        ensureLayoutState();
        resolveShouldLayoutReverse();

        View scrap = recycler.getViewForPosition(0);
        measureChildWithMargins(scrap, 0, 0);
        mDecoratedMeasurement = mOrientationHelper.getDecoratedMeasurement(scrap);
        mDecoratedMeasurementInOther = mOrientationHelper.getDecoratedMeasurementInOther(scrap);
        mSpaceMain = (mOrientationHelper.getTotalSpace() - mDecoratedMeasurement) / 2;
        mSpaceInOther = (getTotalSpaceInOther() - mDecoratedMeasurementInOther) / 2;
        mInterval = setInterval();
        setUp();
        mLeftItems = (int) Math.abs(minRemoveOffset() / mInterval) + 1;
        mRightItems = (int) Math.abs(maxRemoveOffset() / mInterval) + 1;

        if (mPendingSavedState != null) {
            mReverseLayout = mPendingSavedState.isReverseLayout;
            mPendingScrollPosition = mPendingSavedState.position;
            mOffset = mPendingSavedState.offset;
        }

        if (mPendingScrollPosition != RecyclerView.NO_POSITION) {
            mOffset = mReverseLayout ?
                    mPendingScrollPosition * -mInterval : mPendingScrollPosition * mInterval;
        }

        detachAndScrapAttachedViews(recycler);
        layoutItems(recycler);
    }

    public int getTotalSpaceInOther() {
        if (mOrientation == HORIZONTAL) {
            return getHeight() - getPaddingTop()
                    - getPaddingBottom();
        } else {
            return getWidth() - getPaddingLeft()
                    - getPaddingRight();
        }
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        mPendingSavedState = null;
        mPendingScrollPosition = RecyclerView.NO_POSITION;
    }

    void ensureLayoutState() {
        if (mOrientationHelper == null) {
            mOrientationHelper = OrientationHelper.createOrientationHelper(this, mOrientation);
        }
    }

    protected void setUp() {

    }

    private float getProperty(int position) {
        return mReverseLayout ? position * -mInterval : position * mInterval;
    }

    @Override
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
        removeAllViews();
        mOffset = 0;
    }

    @Override
    public void scrollToPosition(int position) {
        mPendingScrollPosition = position;
        mOffset = mReverseLayout ? position * -mInterval : position * mInterval;
        requestLayout();
    }

    @Override
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset();
    }

    @Override
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset();
    }

    @Override
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent();
    }

    @Override
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent();
    }

    @Override
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange();
    }

    @Override
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange();
    }

    private int computeScrollOffset() {
        if (getChildCount() == 0) {
            return 0;
        }

        if (!mSmoothScrollbarEnabled) {
            return !mReverseLayout ?
                    getCurrentPosition() : getItemCount() - getCurrentPosition() - 1;
        }

        final float realOffset = getOffsetOfRightAdapterPosition();
        return !mReverseLayout ? (int) realOffset : (int) ((getItemCount() - 1) * mInterval + realOffset);
    }

    private int computeScrollExtent() {
        if (getChildCount() == 0) {
            return 0;
        }

        if (!mSmoothScrollbarEnabled) {
            return 1;
        }

        return (int) mInterval;
    }

    private int computeScrollRange() {
        if (getChildCount() == 0) {
            return 0;
        }

        if (!mSmoothScrollbarEnabled) {
            return getItemCount();
        }

        return (int) (getItemCount() * mInterval);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
            return 0;
        }
        return scrollBy(dx, recycler, state);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL) {
            return 0;
        }
        return scrollBy(dy, recycler, state);
    }

    private int scrollBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || dy == 0) {
            return 0;
        }
        ensureLayoutState();
        int willScroll = dy;

        float realDx = dy / getDistanceRatio();
        if (Math.abs(realDx) < 0.00000001f) {
            return 0;
        }
        float targetOffset = mOffset + realDx;

        if (!mInfinite && targetOffset < getMinOffset()) {
            willScroll -= (targetOffset - getMinOffset()) * getDistanceRatio();
        } else if (!mInfinite && targetOffset > getMaxOffset()) {
            willScroll = (int) ((getMaxOffset() - mOffset) * getDistanceRatio());
        }

        if (mIntegerDy) {
            realDx = (int) (willScroll / getDistanceRatio());
        } else {
            realDx = willScroll / getDistanceRatio();
        }

        mOffset += realDx;

        for (int i = 0; i < getChildCount(); i++) {
            final View scrap = getChildAt(i);
            final float delta = propertyChangeWhenScroll(scrap) - realDx;
            layoutScrap(scrap, delta);
        }

        layoutItems(recycler);

        return willScroll;
    }

    private void layoutItems(RecyclerView.Recycler recycler) {
        detachAndScrapAttachedViews(recycler);

        final int currentPos = mReverseLayout ?
                -getCurrentPositionOffset() : getCurrentPositionOffset();
        int start = currentPos - mLeftItems;
        int end = currentPos + mRightItems;

        if (useMaxVisibleCount()) {
            boolean isEven = mMaxVisibleItemCount % 2 == 0;
            if (isEven) {
                int offset = mMaxVisibleItemCount / 2;
                start = currentPos - offset + 1;
                end = currentPos + offset + 1;
            } else {
                int offset = (mMaxVisibleItemCount - 1) / 2;
                start = currentPos - offset;
                end = currentPos + offset + 1;
            }
        }

        final int itemCount = getItemCount();
        if (!mInfinite) {
            if (start < 0) {
                start = 0;
                if (useMaxVisibleCount()) end = mMaxVisibleItemCount;
            }
            if (end > itemCount) end = itemCount;
        }

        float lastOrderWeight = Float.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (useMaxVisibleCount() || !removeCondition(getProperty(i) - mOffset)) {
                int adapterPosition = i;
                if (i >= itemCount) {
                    adapterPosition %= itemCount;
                } else if (i < 0) {
                    int delta = (-adapterPosition) % itemCount;
                    if (delta == 0) delta = itemCount;
                    adapterPosition = itemCount - delta;
                }
                final View scrap = recycler.getViewForPosition(adapterPosition);
                measureChildWithMargins(scrap, 0, 0);
                resetViewProperty(scrap);
                final float targetOffset = getProperty(i) - mOffset;
                layoutScrap(scrap, targetOffset);
                final float orderWeight = mEnableBringCenterToFront ?
                        setViewElevation(scrap, targetOffset) : adapterPosition;
                if (orderWeight > lastOrderWeight) {
                    addView(scrap);
                } else {
                    addView(scrap, 0);
                }
                lastOrderWeight = orderWeight;
            }
        }
    }

    private boolean useMaxVisibleCount() {
        return mMaxVisibleItemCount != DETERMINE_BY_MAX_AND_MIN;
    }

    private boolean removeCondition(float targetOffset) {
        return targetOffset > maxRemoveOffset() || targetOffset < minRemoveOffset();
    }

    private void resetViewProperty(View v) {
        v.setRotation(0);
        v.setRotationY(0);
        v.setRotationX(0);
        v.setScaleX(1f);
        v.setScaleY(1f);
        v.setAlpha(1f);
    }

    private float getMaxOffset() {
        return !mReverseLayout ? (getItemCount() - 1) * mInterval : 0;
    }

    private float getMinOffset() {
        return !mReverseLayout ? 0 : -(getItemCount() - 1) * mInterval;
    }

    private void layoutScrap(View scrap, float targetOffset) {
        final int left = calItemLeft(scrap, targetOffset);
        final int top = calItemTop(scrap, targetOffset);
        if (mOrientation == VERTICAL) {
            layoutDecorated(scrap, mSpaceInOther + left, mSpaceMain + top,
                    mSpaceInOther + left + mDecoratedMeasurementInOther, mSpaceMain + top + mDecoratedMeasurement);
        } else {
            layoutDecorated(scrap, mSpaceMain + left, mSpaceInOther + top,
                    mSpaceMain + left + mDecoratedMeasurement, mSpaceInOther + top + mDecoratedMeasurementInOther);
        }
        setItemViewProperty(scrap, targetOffset);
    }

    protected int calItemLeft(View itemView, float targetOffset) {
        return mOrientation == VERTICAL ? 0 : (int) targetOffset;
    }

    protected int calItemTop(View itemView, float targetOffset) {
        return mOrientation == VERTICAL ? (int) targetOffset : 0;
    }

    protected float maxRemoveOffset() {
        return mOrientationHelper.getTotalSpace() - mSpaceMain;
    }

    protected float minRemoveOffset() {
        return -mDecoratedMeasurement - mOrientationHelper.getStartAfterPadding() - mSpaceMain;
    }

    protected float propertyChangeWhenScroll(View itemView) {
        if (mOrientation == VERTICAL)
            return itemView.getTop() - mSpaceMain;
        return itemView.getLeft() - mSpaceMain;
    }

    protected float getDistanceRatio() {
        return 1;
    }

    public int getCurrentPosition() {
        int position = getCurrentPositionOffset();
        if (!mInfinite) return Math.abs(position);
        position = !mReverseLayout ?
                //take care of position = getItemCount()
                (position >= 0 ?
                        position % getItemCount() :
                        getItemCount() + position % getItemCount()) :
                (position > 0 ?
                        getItemCount() - position % getItemCount() :
                        -position % getItemCount());
        return position;
    }

    private int getCurrentPositionOffset() {
        return Math.round(mOffset / mInterval);
    }

    private float getOffsetOfRightAdapterPosition() {
        if (mReverseLayout)
            return mInfinite ?
                    (mOffset <= 0 ?
                            (mOffset % (mInterval * getItemCount())) :
                            (getItemCount() * -mInterval + mOffset % (mInterval * getItemCount()))) :
                    mOffset;
        else
            return mInfinite ?
                    (mOffset >= 0 ?
                            (mOffset % (mInterval * getItemCount())) :
                            (getItemCount() * mInterval + mOffset % (mInterval * getItemCount()))) :
                    mOffset;
    }

    public int getOffsetToCenter() {
        if (mInfinite)
            return (int) ((getCurrentPositionOffset() * mInterval - mOffset) * getDistanceRatio());
        return (int) ((getCurrentPosition() *
                (!mReverseLayout ? mInterval : -mInterval) - mOffset) * getDistanceRatio());
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public void setInfinite(boolean enable) {
        assertNotInLayoutOrScroll(null);
        if (enable == mInfinite) {
            return;
        }
        mInfinite = enable;
        requestLayout();
    }

    public boolean getInfinite() {
        return mInfinite;
    }

    public void setSmoothScrollbarEnabled(boolean enabled) {
        mSmoothScrollbarEnabled = enabled;
    }

    public void setEnableBringCenterToFront(boolean bringCenterToTop) {
        assertNotInLayoutOrScroll(null);
        if (mEnableBringCenterToFront == bringCenterToTop) {
            return;
        }
        this.mEnableBringCenterToFront = bringCenterToTop;
        requestLayout();
    }

    public boolean getEnableBringCenterToFront() {
        return mEnableBringCenterToFront;
    }

    public boolean getSmoothScrollbarEnabled() {
        return mSmoothScrollbarEnabled;
    }

    private static class SavedState implements Parcelable {
        int position;
        float offset;
        boolean isReverseLayout;

        SavedState() {

        }

        SavedState(Parcel in) {
            position = in.readInt();
            offset = in.readFloat();
            isReverseLayout = in.readInt() == 1;
        }

        public SavedState(SavedState other) {
            position = other.position;
            offset = other.offset;
            isReverseLayout = other.isReverseLayout;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(position);
            dest.writeFloat(offset);
            dest.writeInt(isReverseLayout ? 1 : 0);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public interface OnPageChangeListener {
        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
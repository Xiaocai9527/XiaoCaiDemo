package com.xiaokun.xiusou.demo6.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.xiaokun.xiusou.demo6.R;

import java.io.File;

public class PicassoUtil {

	/**
	 * 指定大小加载图片
	 * 
	 * @param mContext
	 *            上下�?
	 * @param path
	 *            图片路径
	 * @param width
	 *            �?
	 * @param height
	 *            �?
	 * @param mImageView
	 *            控件
	 */
	public static void loadImageViewSize(Context mContext, String path,
			int width, int height, ImageView mImageView) {
		// context = mContext;
		Picasso.with(mContext).load(path).resize(width, height).centerCrop()
				.placeholder(R.drawable.icon_stub).error(R.drawable.icon_error)
				.config(Bitmap.Config.RGB_565).into(mImageView);
		// Picasso.with(mContext).setIndicatorsEnabled(true);
	}

	/**
	 * 加载有默认图�?
	 * 
	 * @param mContext
	 *            上下�?
	 * @param path
	 *            图片路径
	 * @param mImageView
	 *            控件
	 */
	public static void loadImageViewHolder(Context mContext, String path,
			ImageView mImageView) {
		Picasso.with(mContext).load(path).fit()
				.placeholder(R.drawable.icon_stub).into(mImageView);
	}

	public static void loadImageViewHolder2(Context mContext, File file,
			ImageView mImageView) {
		Picasso.with(mContext).load(file).fit()
				.placeholder(R.drawable.icon_stub).into(mImageView);
	}

	/**
	 * 裁剪图片
	 * 
	 * @param mContext
	 *            上下�?
	 * @param path
	 *            图片路径
	 * @param mImageView
	 *            控件
	 */
	public static void loadImageViewCrop(Context mContext, String path,
			ImageView mImageView) {
		Picasso.with(mContext).load(path).transform(new CropImageView())
				.into(mImageView);
	}

	/**
	 * 自定义图片裁�?
	 */
	public static class CropImageView implements Transformation {

		@Override
		public Bitmap transform(Bitmap source) {
			int size = Math.min(source.getWidth(), source.getHeight());
			int x = (source.getWidth() - size) / 2;
			int y = (source.getHeight() - size) / 2;

			Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);

			if (newBitmap != null) {
				// 内存回收
				source.recycle();
			}
			return newBitmap;
		}

		@Override
		public String key() {

			return "lgl";
		}
	}
}

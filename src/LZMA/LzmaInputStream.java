package LZMA;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LzmaInputStream extends FilterInputStream
{
	boolean isClosed;
	CRangeDecoder RangeDecoder;
	byte[] dictionary;
	int dictionarySize;
	int dictionaryPos;
	int GlobalPos;
	int rep0;
	int rep1;
	int rep2;
	int rep3;
	int lc;
	int lp;
	int pb;
	int State;
	boolean PreviousIsMatch;
	int RemainLen;
	int[] probs;
	byte[] uncompressed_buffer;
	int uncompressed_size;
	int uncompressed_offset;
	long GlobalNowPos;
	long GlobalOutSize;
	static final int LZMA_BASE_SIZE = 1846;
	static final int LZMA_LIT_SIZE = 768;
	static final int kBlockSize = 65536;
	static final int kNumStates = 12;
	static final int kStartPosModelIndex = 4;
	static final int kEndPosModelIndex = 14;
	static final int kNumFullDistances = 128;
	static final int kNumPosSlotBits = 6;
	static final int kNumLenToPosStates = 4;
	static final int kNumAlignBits = 4;
	static final int kAlignTableSize = 16;
	static final int kMatchMinLen = 2;
	static final int IsMatch = 0;
	static final int IsRep = 192;
	static final int IsRepG0 = 204;
	static final int IsRepG1 = 216;
	static final int IsRepG2 = 228;
	static final int IsRep0Long = 240;
	static final int PosSlot = 432;
	static final int SpecPos = 688;
	static final int Align = 802;
	static final int LenCoder = 818;
	static final int RepLenCoder = 1332;
	static final int Literal = 1846;

	public LzmaInputStream(InputStream paramInputStream)
			throws IOException
			{
		super(paramInputStream);

		this.isClosed = false;

		readHeader();

		fill_buffer();
			}

	private void LzmaDecode(int paramInt) throws IOException
	{
		int j = (1 << this.pb) - 1;
		int k = (1 << this.lp) - 1;

		this.uncompressed_size = 0;

		if (this.RemainLen == -1)
			return;
		int m;
		while ((this.RemainLen > 0) && (this.uncompressed_size < paramInt)) {
			m = this.dictionaryPos - this.rep0;
			if (m < 0)
				m += this.dictionarySize;
			byte tmp103_102 = this.dictionary[m]; this.dictionary[this.dictionaryPos] = tmp103_102; this.uncompressed_buffer[(this.uncompressed_size++)] = tmp103_102;
			if (++this.dictionaryPos == this.dictionarySize)
				this.dictionaryPos = 0;
			this.RemainLen -= 1;
		}
		byte i;
		if (this.dictionaryPos == 0)
			i = this.dictionary[(this.dictionarySize - 1)];
		else {
			i = this.dictionary[(this.dictionaryPos - 1)];
		}
		while (this.uncompressed_size < paramInt) {
			m = this.uncompressed_size + this.GlobalPos & j;
			int n;
			int i1;
			if (this.RangeDecoder.BitDecode(this.probs, 0 + (this.State << 4) + m) == 0) {
				n = 1846 + 768 * (((this.uncompressed_size + this.GlobalPos & k) << this.lc) + ((i & 0xFF) >> 8 - this.lc));

				if (this.State < 4)
					this.State = 0;
				else if (this.State < 10)
					this.State -= 3;
				else
					this.State -= 6;
				if (this.PreviousIsMatch) {
					i1 = this.dictionaryPos - this.rep0;
					if (i1 < 0)
						i1 += this.dictionarySize;
					byte b = this.dictionary[i1];

					i = this.RangeDecoder.LzmaLiteralDecodeMatch(this.probs, n, b);
					this.PreviousIsMatch = false;
				} else {
					i = this.RangeDecoder.LzmaLiteralDecode(this.probs, n);
				}

				this.uncompressed_buffer[(this.uncompressed_size++)] = i;

				this.dictionary[this.dictionaryPos] = i;
				if (++this.dictionaryPos == this.dictionarySize)
					this.dictionaryPos = 0;
			}
			else {
				this.PreviousIsMatch = true;
				if (this.RangeDecoder.BitDecode(this.probs, 192 + this.State) == 1) {
					if (this.RangeDecoder.BitDecode(this.probs, 204 + this.State) == 0) {
						if (this.RangeDecoder.BitDecode(this.probs, 240 + (this.State << 4) + m) == 0)
						{
							if (this.uncompressed_size + this.GlobalPos == 0) {
								throw new LzmaException("LZMA : Data Error");
							}
							this.State = (this.State < 7 ? 9 : 11);

							n = this.dictionaryPos - this.rep0;
							if (n < 0)
								n += this.dictionarySize;
							i = this.dictionary[n];
							this.dictionary[this.dictionaryPos] = i;
							if (++this.dictionaryPos == this.dictionarySize) {
								this.dictionaryPos = 0;
							}
							this.uncompressed_buffer[(this.uncompressed_size++)] = i;
							continue;
						}
					}
					else {
						if (this.RangeDecoder.BitDecode(this.probs, 216 + this.State) == 0) {
							n = this.rep1;
						} else {
							if (this.RangeDecoder.BitDecode(this.probs, 228 + this.State) == 0) {
								n = this.rep2;
							} else {
								n = this.rep3;
								this.rep3 = this.rep2;
							}
							this.rep2 = this.rep1;
						}
						this.rep1 = this.rep0;
						this.rep0 = n;
					}
					this.RemainLen = this.RangeDecoder.LzmaLenDecode(this.probs, 1332, m);
					this.State = (this.State < 7 ? 8 : 11);
				} else {
					this.rep3 = this.rep2;
					this.rep2 = this.rep1;
					this.rep1 = this.rep0;
					this.State = (this.State < 7 ? 7 : 10);
					this.RemainLen = this.RangeDecoder.LzmaLenDecode(this.probs, 818, m);
					n = this.RangeDecoder.BitTreeDecode(this.probs, 432 + ((this.RemainLen < 4 ? this.RemainLen : 3) << 6), 6);

					if (n >= 4) {
						i1 = (n >> 1) - 1;
						this.rep0 = ((0x2 | n & 0x1) << i1);
						if (n < 14) {
							this.rep0 += this.RangeDecoder.ReverseBitTreeDecode(this.probs, 688 + this.rep0 - n - 1, i1);
						}
						else {
							this.rep0 += (this.RangeDecoder.DecodeDirectBits(i1 - 4) << 4);

							this.rep0 += this.RangeDecoder.ReverseBitTreeDecode(this.probs, 802, 4);
						}
					} else {
						this.rep0 = n;
					}this.rep0 += 1;
				}
				if (this.rep0 == 0)
				{
					this.RemainLen = -1;
					break;
				}
				if (this.rep0 > this.uncompressed_size + this.GlobalPos)
				{
					throw new LzmaException("LZMA : Data Error");
				}
				this.RemainLen += 2;
				do
				{
					n = this.dictionaryPos - this.rep0;
					if (n < 0)
						n += this.dictionarySize;
					i = this.dictionary[n];
					this.dictionary[this.dictionaryPos] = i;
					if (++this.dictionaryPos == this.dictionarySize) {
						this.dictionaryPos = 0;
					}
					this.uncompressed_buffer[(this.uncompressed_size++)] = i;
					this.RemainLen -= 1;
				}while ((this.RemainLen > 0) && (this.uncompressed_size < paramInt));
			}
		}

		this.GlobalPos += this.uncompressed_size;
	}

	private void fill_buffer() throws IOException {
		if (this.GlobalNowPos < this.GlobalOutSize) {
			this.uncompressed_offset = 0;
			long l = this.GlobalOutSize - this.GlobalNowPos;
			int i;
			if (l > 65536L)
				i = 65536;
			else {
				i = (int)l;
			}
			LzmaDecode(i);

			if (this.uncompressed_size == 0)
				this.GlobalOutSize = this.GlobalNowPos;
			else
				this.GlobalNowPos += this.uncompressed_size;
		}
	}

	private void readHeader() throws IOException
	{
		byte[] arrayOfByte = new byte[5];

		if (5 != this.in.read(arrayOfByte)) {
			throw new LzmaException("LZMA header corrupted : Properties error");
		}
		this.GlobalOutSize = 0L;
		for (int i = 0; i < 8; i++) {
			int j = this.in.read();
			if (j == -1)
				throw new LzmaException("LZMA header corrupted : Size error");
			this.GlobalOutSize += (j << i * 8);
		}

		if (this.GlobalOutSize == -1L) this.GlobalOutSize = 9223372036854775807L;

		int i = arrayOfByte[0] & 0xFF;
		if (i >= 225) {
			throw new LzmaException("LZMA header corrupted : Properties error");
		}

		for (this.pb = 0; i >= 45; i -= 45) this.pb += 1;

		for (this.lp = 0; i >= 9; i -= 9) this.lp += 1;

		this.lc = i;

		int j = 1846 + (768 << this.lc + this.lp);

		this.probs = new int[j];

		this.dictionarySize = 0;
		for (int k = 0; k < 4; k++)
			this.dictionarySize += ((arrayOfByte[(1 + k)] & 0xFF) << k * 8);
		this.dictionary = new byte[this.dictionarySize];
		if (this.dictionary == null) {
			throw new LzmaException("LZMA : can't allocate");
		}

		int k = 1846 + (768 << this.lc + this.lp);

		this.RangeDecoder = new CRangeDecoder(this.in);
		this.dictionaryPos = 0;
		this.GlobalPos = 0;
		this.rep0 = (this.rep1 = this.rep2 = this.rep3 = 1);
		this.State = 0;
		this.PreviousIsMatch = false;
		this.RemainLen = 0;
		this.dictionary[(this.dictionarySize - 1)] = 0;
		for (int m = 0; m < k; m++) {
			this.probs[m] = 1024;
		}
		this.uncompressed_buffer = new byte[65536];
		this.uncompressed_size = 0;
		this.uncompressed_offset = 0;

		this.GlobalNowPos = 0L;
	}

	public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException {
		if (this.isClosed) {
			throw new IOException("stream closed");
		}
		if ((paramInt1 | paramInt2 | paramInt1 + paramInt2 | paramArrayOfByte.length - (paramInt1 + paramInt2)) < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (paramInt2 == 0) {
			return 0;
		}
		if (this.uncompressed_offset == this.uncompressed_size)
			fill_buffer();
		if (this.uncompressed_offset == this.uncompressed_size) {
			return -1;
		}
		int i = Math.min(paramInt2, this.uncompressed_size - this.uncompressed_offset);
		System.arraycopy(this.uncompressed_buffer, this.uncompressed_offset, paramArrayOfByte, paramInt1, i);
		this.uncompressed_offset += i;
		return i;
	}

	public void close() throws IOException {
		this.isClosed = true;
		super.close();
	}
}
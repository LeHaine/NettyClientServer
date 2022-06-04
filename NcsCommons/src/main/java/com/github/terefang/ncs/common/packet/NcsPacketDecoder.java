package com.github.terefang.ncs.common.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

public class NcsPacketDecoder extends MessageToMessageDecoder<ByteBuf>
{
    NcsPacketFactory<AbstractNcsPacket> _factory;

    public NcsPacketDecoder(NcsPacketFactory<AbstractNcsPacket> _factory) {
        super();
        this._factory = _factory;
    }

    @Override
    protected void decode(ChannelHandlerContext _ctx, ByteBuf _buf, List<Object> _list) throws Exception
    {
        NcsPacket _pkt = this._factory.unpack(_buf);
        _pkt.setAddress((InetSocketAddress) _ctx.channel().remoteAddress());
        _list.add(_pkt);
    }
}

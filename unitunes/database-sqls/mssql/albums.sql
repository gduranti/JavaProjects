USE [unitunes]
GO

/****** Object:  Table [dbo].[ALBUMS]    Script Date: 11/16/2014 20:08:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[ALBUMS](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[description] [varchar](255) NOT NULL,
	[publishedDate] [datetime2](7) NOT NULL,
	[author_id] [bigint] NOT NULL,
	[thumb] [varbinary](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[ALBUMS]  WITH CHECK ADD  CONSTRAINT [FK733943E46A733ADE] FOREIGN KEY([author_id])
REFERENCES [dbo].[USERS] ([id])
GO

ALTER TABLE [dbo].[ALBUMS] CHECK CONSTRAINT [FK733943E46A733ADE]
GO

